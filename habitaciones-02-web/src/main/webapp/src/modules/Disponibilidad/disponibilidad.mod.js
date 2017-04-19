(function (ng) {
    // Definición del módulo
    var mod = ng.module("disponibilidadModule", ['ui.router']);
    mod.constant("disponibilidadContext", "api/habitaciones/{idHabitacion:int}/disponibilidades");

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Disponibilidad/';
            // Mostrar la lista de dispponibilidades será el estado por defecto del módulo
            //$urlRouterProvider.otherwise("/disponibilidadesList");
            // Definición del estado 'disponibilidadesList' donde se listan las disponibilidades
            $stateProvider.state('disponibilidades', {
                // Url que aparecerá en el browser
                url: '/disponibilidades',
                abstract: true,

                resolve: {
                    disponibilidades: ['$http', 'disponibilidadContext', function ($http, disponibilidadContext) {
                            return $http.get(disponibilidadContext); // $http retorna una promesa que aquí no se está manejando si viene con error.
                        }]
                },
                views: {
                    'mainView': {

                        templateUrl: basePath + 'disponibilidad.html',
                        controller: 'disponibilidadListCtrl'

                    }
                }
            }).state('disponibilidadesList', {
                url: '/disponibilidades/list',

                resolve: {
                    disponibilidades: ['$http', 'disponibilidadContext', function ($http, disponibilidadContext) {
                            return $http.get(disponibilidadContext);
                        }]


                },

                views: {
                    'mainView': {
                        templateUrl: basePath + 'disponibilidad.list.html',
                        controller: 'disponibilidadListCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            });

            $stateProvider.state('disponibilidadDetail', {
                url: '/{disponibilidadId:int}/detail',
                parent: 'disponibilidades',
                param: {
                    disponibilidadId: null
                },
               views: {
                    'listView': {
                        templateUrl: basePath + 'disponibilidad.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'disponibilidad.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentDisponibilidad = $scope.disponibilidadesRecords[$params.disponibilidadId-1];
                            }]
                    }

                }
            });

        }
    ]);
})(window.angular);
