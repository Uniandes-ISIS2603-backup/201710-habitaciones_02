(function (ng) {
    // Definición del módulo
    var mod = ng.module("habitacionModule", ['ui.router']);
    mod.constant("habitacionContext", "api/habitaciones");
 
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Habitacion/';
            // Mostrar la lista de habitaciones será el estado por defecto del módulo
            
            // Definición del estado 'habitacionesList' donde se listan las habitaciones
            $stateProvider.state('habitaciones', {
                // Url que aparecerá en el browser
                url: '/habitaciones',
                abstract: true,
                // Se define una variable habitaciones (del estado) que toma por valor 
                // la colección de habitaciones que obtiene utilizando $http.get 
                 resolve: {
                    habitaciones: ['$http', 'habitacionContext', function ($http, habitacionContext) {
                            return $http.get(habitacionContext); // $http retorna una promesa que aquí no se está manejando si viene con error.
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'habitacion.html',
                        controller: 'habitacionListCtrl'
                            
                    }
                }
            }).state('habitacionesList', {
                url: '/habitaciones/list',
               
                 resolve: {
                    habitaciones: ['$http', 'habitacionContext', function ($http, habitacionContext) {
                            return $http.get(habitacionContext); 
                        }]
                    
                    
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'habitacion.list.html',
                        controller: 'habitacionListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('habitacionDetail', {
                url: '/{habitacionId:int}/detail',
                parent: 'habitaciones',
                param: {
                    habitacionId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'habitacion.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'habitacion.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentHabitacion = $scope.habitacionesRecords[$params.habitacionId-1];
                            }]
                    }

                }
           
            });
        }
    ]);
})(window.angular);
