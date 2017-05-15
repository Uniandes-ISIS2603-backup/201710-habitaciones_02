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

//            $stateProvider.state('filtrarViviendas', {
//                url: '/ciudad/{ciudad:String}',
//                parent: 'viviendasList',
//                param: {
//                    ciudad: null
//                },
//                resolve: {
//                    viviendas: ['$http', '$scope', 'viviendasContext', function ($http, $params, viviendasContext) {
//                            $params.vivienda = {};
//                            $params.filtrar = function () {
//                                return $http.get(viviendasContext + '/' + $params.vivienda.ciudad);
//                            };
//                        }]
//                },
//                views: {
//                    'listview': {
//                        templateUrl: basePath + 'vivienda.list.html'
//                    }
//                }
//            });

            $stateProvider.state('createVivienda', {
                url: '/registrarVivienda',
                views: {
                    'mainView': {
                        templateUrl: basePath + "vivienda.create.html"
                    }
                }
            });

//            $stateProvider.state('filtrarViviendas',{
//                url: '/ciudad/{viviendaCiudad:String}',
//                parent: 'viviendasList',
//                param:{
//                    viviendaCiudad:null
//                },
//                resolve:{
//                    viviendas:['$http','viviendasContext','$stateParams','$http',
//                        function($scope,viviendasContext,$stateParams,$http){
//                            $stateParams = {};
//                            $stateParams.ciudad = function()
//                            {
//                                return $http.get( viviendasContext + "/" + $stateParams.ciudad)
//                            }
//                        }
//                    ]
//                }
//            })
        }
    ]);
})(window.angular);
