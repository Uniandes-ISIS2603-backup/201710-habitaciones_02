(function (ng) { 
    // Definición del módulo
    var mod = ng.module("viajeroModule", ['ui.router']);
        mod.constant("viajeroContext","api/viajeros");
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Viajero/';
            // Mostrar la lista de viajeros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/viajerosList");
            
            // Definición del estado 'ViajerosList' donde se listan los libros
            $stateProvider.state('viajero', {
                
                url: '/viajeros',
                abstract: true,
                resolve: {
                    viajeros: ['$http', function ($http) {
                            return $http.get('data/viajeros.json');
                        }]
                },
                views: {
                    'mainView': {
                        
                       
                        templateUrl: basePath + 'viajero.html',
                        controller: ['$scope', 'viajeros', function ($scope, viajeros) {
                                $scope.RecordsViajero = viajeros.data;
                            }]
                    }
                }
            }).state('viajerosList',  {
                // Url que aparecerá en el browser
                url: '/viajeros/list',
                parent: 'viajero',
                
                views:{
                    'listView': {
                        templateUrl: basePath + 'viajero.list.html',
                        controller: 'viajeroListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
               
            }).state('viajeroDetail', {
                url: '/{viajeroId:int}',
                parent: 'viajero',
                param: {
                    viajeroId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'viajero.carousel.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentViajero = $scope.RecordsViajero[$params.viajeroId-16];
                                $scope.nextViajero = $scope.RecordsViajero[$params.viajeroId-15];
                                $scope.prevViajero = $scope.RecordsViajero[$params.viajeroId-17];
                            }]
                    },
                    'detailView': {
                        templateUrl: basePath + 'viajero.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentViajero = $scope.RecordsViajero[$params.viajeroId-16];
                            }]
                    }

                } 
            });
            $stateProvider.state('viajeroReservasList', {
                url: '/reservas',
                parent: 'viajeroDetail',
                
                param: {
                    viajeroId: null
                },
                views: {
                    'ListasViajeroView':{
                        templateUrl: basePath + 'viajero.reservas.list.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentViajero = $scope.RecordsViajero[$params.viajeroId-16];
                            }]
                        
                    }
                } 
            });
            $stateProvider.state('viajeroResenasList', {
                url: '/resenas',
                parent: 'viajeroDetail',
                
                param: {
                    viajeroId: null
                },
                views: {
                    'ListasViajeroView':{
                        templateUrl: basePath + 'viajero.resenas.list.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentViajero = $scope.RecordsViajero[$params.viajeroId-16];
                            }]
                        
                    }
                } 
            });
            
            $stateProvider.state('viajeroCreate', {
                url: '/',
                
                resolve: {
                    viajeros: ['$http', function ($http) {
                            return $http.get('data/viajeros.json');
                        }]
                },
                views: {
                    
                    'mainView': {
                       
                        templateUrl: basePath + 'viajero.create.html',
                        controller: ['$scope', 'viajeros', function ($scope, viajeros) {
                                $scope.RecordsViajero = viajeros.data;
                            }]
                    }
                } 
            });
            
            $stateProvider.state('viajeroUpdate', {
                url: '/{viajeroId:int}',
                parent: 'viajeroDetail',
                
                param: {
                    viajeroId: null
                },
                views: {
                    'ListasViajeroView':{
                        templateUrl: basePath + 'viajero.update.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentViajero = $scope.RecordsViajero[$params.viajeroId-16];
                            }]
                        
                    }
                } 
            });
            
            
        }
    ]);
})(window.angular);