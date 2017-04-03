(function (ng) {
    // Definición del módulo
    var mod = ng.module("resenaModule", ['ui.router']);
    
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Resena/';
            // Mostrar la lista de resenas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/resenasList");
            
            $stateProvider.state('resena', {
                
                 url: '/resenas',
                 abstract: true,
                 
                resolve: {
                    resenas: ['$http', function ($http) {
                            return $http.get('data/resenas.json');
                        }]
                    
                },
                views: {
                    'mainView': {
                        
                       
                        templateUrl: basePath + 'resena.html',
                        controller: ['$scope', 'resenas', function ($scope, resenas) {
                                $scope.RecordsResena = resenas.data;
                            }]
                    }
                }
                
                
            }).state('resenasList', {
                // Url que aparecerá en el browser
                url: '/resenas/list',
                
                resolve: 
                {
                    resenas: ['$http', function ($http) 
                    {
                        return $http.get('data/resenas.json'); // $http retorna un apromesa que aquí no se está manejando si viene con error.
                    }]
                },
                views:{
                    mainView: {
                        templateUrl: basePath + 'resena.list.html',
                        controller: 'resenaListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                
            });
            $stateProvider.state('resenaDetail', {

                url: '/{resenaId:int}',
                parent: 'resena',
                param: {
                    resenaId: null,
                    viajeroId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'resena.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params ) { 
                               $scope.currentResena = $scope.RecordsResena[$params.resenaId-1];
                            }]
                    }

                } 
            });
            $stateProvider.state('resenaDetailViajero', {

                url: '/{resenaId:int}/viajeros/{viajeroId:int}',
                parent: 'resena',
                param: {
                    resenaId: null,
                    viajeroId: null
                },
                resolve:{
                  
                    viajeros: ['$http', function ($http) 
                    {
                        return $http.get('data/viajeros.json'); // $http retorna un apromesa que aquí no se está manejando si viene con error.
                    }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'resena.detail.viajero.html',
                        controller: ['$scope', '$stateParams','viajeros', function ($scope, $params, viajeros ) { 
                               $scope.currentResena = $scope.RecordsResena[$params.resenaId-1];
                               $scope.RecordsViajero = viajeros.data;
                               $scope.currentViajero = $scope.RecordsViajero[$params.viajeroId-16];
                               
                            }]
                    }

                } 
            });
            $stateProvider.state('resenaDetailHabitacion', {

                url: '/{resenaId:int/habitaciones/{habitacionId:int}}',
                parent: 'resena',
                param: {
                    resenaId: null,
                    habitacionId: null
                },
                resolve:{
                  
                    habitaciones: ['$http', function ($http) 
                    {
                        return $http.get('data/habitaciones.json'); // $http retorna un apromesa que aquí no se está manejando si viene con error.
                    }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'resena.detail.habitacion.html',
                        controller: ['$scope', '$stateParams','habitaciones', function ($scope, $params, habitaciones ) { 
                               $scope.currentResena = $scope.RecordsResena[$params.resenaId-1];
                               $scope.RecordsHabitacion = habitaciones.data;
                               $scope.currentHabitaciones = $scope.RecordsHabitacion[$params.habitacionId-16];
                               
                            }]
                    }

                } 
            });
            $stateProvider.state('resenaUpdate', {
                url: '/{resenaId:int}/viajeros/{viajeroId:int}',
                parent: 'resenaDetailViajero',
                
                param: {
                    resenaId: null
                },
                views: {
                    'resenaUpdateView':{
                        templateUrl: basePath + 'resena.update.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                 $scope.currentResena = $scope.RecordsResena[$params.resenaId-1];
                            }]
                        
                    }
                } 
            });
            
        }
    ]);
})(window.angular);