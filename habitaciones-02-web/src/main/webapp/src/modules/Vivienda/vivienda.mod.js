(function (ng) {
    // Definición del módulo
    var mod = ng.module("viviendaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Vivienda/';
            var basePathHabitaciones = 'src/modules/Habitacion/';
            $stateProvider.state('viviendas', {
                url: '/viviendas',
                abstract: true,
                resolve: {
                    viviendas: ['$http', function ($http) {
                            return $http.get('data/viviendas.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'vivienda.html',
                        controller: ['$scope', 'viviendas', function ($scope, viviendas) {
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
                views: {
                    'listView': {
                        templateUrl: basePath + 'viviendaHabs.list.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentVivienda = $scope.viviendasRecords[$params.viviendaId-1];
                            }]
                    },
                    'detailView': {
                        templateUrl: basePath + 'vivienda.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentVivienda = $scope.viviendasRecords[$params.viviendaId-1];
                            }]
                    }

                }
           
            });
        }
    ]);
})(window.angular);
