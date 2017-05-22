(function (ng) {
    // Definición del módulo
    var mod = ng.module("reservaModule", ['ui.router']);
    mod.constant("reservasContext", "api/reservas");

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Reserva/';
            // Mostrar la lista de libros será el estado por defecto del módulo
            
            // Definición del estado 'booksList' donde se listan los libros
            $stateProvider.state('reserva', {
                // Url que aparecerá en el browser
                url: '/reservas',
                // Se define una variable reservas (del estado) que toma por valor  para el json
                // la colección de libros que obtiene utilizando $http.get 
                resolve: {
                     reservas: ['$http', 'reservasContext',
                        function ($http, reservasContext) {
                            return $http.get(reservasContext);
                        }]
                },
                // Template que se utilizara para ejecutar el estado
                views: {
                    'mainView': {
                        // Template que se utilizara para ejecutar el estado
                        templateUrl: basePath + 'reserva.html',
                        // El controlador guarda en el scope en la variable reservaRecords los datos que trajo el resolve
                        // reservaRecords será visible en el template
                        controller: 'reservaListCtrl',

                    }
                }
            }).state('reservasList', {
                // Url que aparecerá en el browser
                url: '/reservas/list',
                // Se define una variable anfitriones (del estado) que toma por valor 
                // la colección de anfitriones que obtiene utilizando $http.get 

                resolve: {
                     reservas: ['$http', 'reservasContext',
                        function ($http, reservasContext) {
                            return $http.get(reservasContext);
                        }],
                    viajeros: ['$http', function ($http) {
                            return $http.get('data/viajeros.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'reserva.list.html',
                        controller: 'reservaListCtrl',
                        controllerAs: 'ctrl',
                    }
                }
            });
            $stateProvider.state('reservaDetail', {
                url: '/{reservaId:int}/detail',
                parent: 'reserva',
                param: {
                    reservaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'reserva.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'reserva.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentReserva = $scope.reservasRecords[$params.reservaId - 1];
                            }]
                    }

                }
            });
            $stateProvider.state('reservaViajeroList', {
                url: '/{reservaId:int}/viajero',
                parent: 'reservaDetail',

                param: {
                    reservaId: null
                },
                views: {
                    'ListasReservaView': {
                        templateUrl: basePath + 'reserva.viajero.list.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentReserva = $scope.reservasRecords[$params.reservaId];
                            }]

                    }
                }
            });

            $stateProvider.state('reservaAnfitrionList', {
                url: '/{reservaId:int}/anfitrion',
                parent: 'reservaDetail',

                param: {
                    reservaId: null
                },
                views: {
                    'ListasReservaView': {
                        templateUrl: basePath + 'reserva.anfitrion.list.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentReserva = $scope.reservasRecords[$params.reservaId];
                            }]

                    }
                }
            } );
            
             $stateProvider.state('reservaoptionlist', {
                url: '/{reservaId:int}/option',
                parent: 'reservaDetail',

                param: {
                    reservaId: null
                },
                views: {
                    'ListasReservaView': {
                        templateUrl: basePath + 'reserva.option.list.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentReserva = $scope.reservasRecords[$params.reservaId-1];
                            }]

                    }
                }
            });             $stateProvider.state('reservaCreate', {
                url: '/{reservaId:int}/create',
                parent: 'reservaDetail',

                param: {
                    reservaId: null
                },
                views: {
                    'ListasReservaView': {
                        templateUrl: basePath + 'reserva.create.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentReserva = $scope.reservasRecords[$params.reservaId-1];
                            }]

                    }
                }
            });
                 $stateProvider.state('reservadisp', {
                url: '/{reservaId:int}/disponible',
                parent: 'reservaDetail',

                param: {
                    reservaId: null,
                    reservac: true
                },
                views: {
                    'ListasReservaView': {
                        templateUrl: basePath + 'reserva.list.disp.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentReserva = $scope.reservasRecords[$params.reservac];
                            }]

                    }
                }
            } );

        }
    ]);
})(window.angular);
