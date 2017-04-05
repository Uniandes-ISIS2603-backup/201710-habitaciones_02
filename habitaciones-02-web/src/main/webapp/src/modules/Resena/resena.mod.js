(function (ng) {
    // Definición del módulo
    var mod = ng.module("resenaModule", ['ui.router']);
        mod.constant("resenaContext","api/resenas");
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Resena/';
            // Mostrar la lista de resenas será el estado por defecto del módulo
            
            $stateProvider.state('resena', {
                
                 url: '/resenas',
                 abstract: true,
                 parent:'viajeroDetail',

                resolve: {
                    resenas: ['$http','resenaContext','$stateParams', function ($http, resenaContext, $stateParams) {
                            return $http.get(resenaContext + '/porViajero/'+ $stateParams.viajeroId);
                        }]
                    
                },
                views: {
                    'ListasViajeroView': {

                        templateUrl: basePath + 'resena.html',
                        controller: ['$scope', 'resenas', function ($scope, resenas) {
                                $scope.RecordsResena = resenas.data;
                            }]
                    }
                }
                
                
            }).state('viajeroResenasList', {
                url: '/list',
                parent: 'resena',
                
                views:{
                    'listViewResena':{
                        templateUrl: basePath + 'viajero.resenas.list.html',
                        controller: ['$scope', 'resenas', function ($scope, resenas) {
                                $scope.RecordsResena = resenas.data;
                            }]
                        //hereda el currentViajero del viajeroDetail??
                    }
                }
                
            }).state('resenaDetailViajero', {

                url: '/detail/{resenaId:int}',
                parent: 'resena',
                param: {
                    resenaId: null
                },
                resolve:{
                    currentResena:['$http', 'resenaContext', '$stateParams', function ($http, resenaContext, $params) {
                            return $http.get(resenaContext+'/'+$params.resenaId);
                    }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'resena.detail.viajero.html',
                        controller: ['$scope', 'currentResena', function ($scope, currentResena ) { 
                               $scope.currentResena = currentResena.data;           
                            }]
                    }

                } 
            }).state('resenaUpdate', {
                url: '/update',
                parent: 'resenaDetailViajero',
                
                param: {
                    resenaId: null
                },
                views: {
                    'resenaUpdateView':{
                        templateUrl: basePath + 'resena.update.html'
                        
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
            
            
            
            
            
        }
    ]);
})(window.angular);