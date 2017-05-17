(function (ng) {
    // Definición del módulo
    var mod = ng.module("viviendaModule", ['ui.router']);

    mod.constant("viviendasContext", "api/viviendas");

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Vivienda/';
            $stateProvider.state('viviendas', {
                url: '/viviendas',
                abstract: true,
                resolve: {
                    viviendas: ['$http', 'viviendasContext',
                        function ($http, viviendasContext) {
                            return $http.get(viviendasContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'vivienda.html',
                        controller: ['$scope', 'viviendas',
                            function ($scope, viviendas) {
                                $scope.viviendasRecords = viviendas.data;
                            }]
                    }
                }
            }).state('viviendasList', {
                url: '/list',
                parent: 'viviendas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'vivienda.list.html'
                    }
                }
            }).state('viviendaDetail', {
                url: '/{viviendaId:int}/detail',
                parent: 'viviendas',
                param: {
                    viviendaId: null
                },
                resolve: {
                    currentVivienda: ['$http', 'viviendasContext', '$stateParams',
                        function ($http, viviendasContext, $params) {
                            return $http.get(viviendasContext + '/' + $params.viviendaId);
                        }]
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'viviendaHabs.list.html',
                        controller: ['$scope', 'currentVivienda',
                            function ($scope, currentVivienda) {
                                $scope.currentVivienda = currentVivienda.data;
                            }]
                    },
                    'detailView': {
                        templateUrl: basePath + 'vivienda.detail.html',
                        controller: ['$scope', 'currentVivienda',
                            function ($scope, currentVivienda) {
                                $scope.currentVivienda = currentVivienda.data;
                            }]
                    }

                }

            });

            $stateProvider.state('viviendaFiltrar', {
                url: '/{viviendaCiudad}',
                parent: 'viviendas',
                param: {
                    viviendaCiudad: null
                },
                resolve: {
                    viviendas: ['$http', '$stateParams', 'viviendasContext', function ($http, $params, viviendasContext) {
                            return $http.get(viviendasContext + '/' + $params.viviendaCiudad);
                        }]
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'vivienda.list.html',
                        controller: ['$scope', 'viviendas',
                            function ($scope, viviendas) {
                                $scope.viviendasRecords = viviendas.data;
                            }]
                    }
                }
            });

            $stateProvider.state('createVivienda', {
                url: '/registrarVivienda',
                views: {
                    'mainView': {
                        templateUrl: basePath + "vivienda.create.html",
                        controller: 'viviendaCrearCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
