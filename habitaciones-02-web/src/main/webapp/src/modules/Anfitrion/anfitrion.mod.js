(function (ng) {
    // Definición del módulo
    var mod = ng.module("anfitrionModule", ['ui.router']);
    mod.constant("anfitrionesContext", "api/anfitriones");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Anfitrion/';

            var presente = 0;
            // Definición del estado 'anfitrionesList' 
            // donde se listan los anfitriones
            $stateProvider.state('anfitriones', {
                url: '/anfitriones',
                abstract: true,

                resolve: {
                    anfitriones: ['$http', 'anfitrionesContext',
                        function ($http, anfitrionesContext) {
                            return $http.get(anfitrionesContext);
                        }]

                },
                views: {
                    'mainView': {

                        templateUrl: basePath + 'anfitrion.html',
                        controller: 'anfitrionListCtrl'
                    }
                }
            }).state('anfitrionesList', {
                // Url que aparecerá en el browser
                url: '/anfitriones/list',
                // Se define una variable anfitriones (del estado)
                // que toma por valor 
                // la colección de anfitriones que obtiene utilizando $http.get 

                resolve: {
                    anfitriones: ['$http', 'anfitrionesContext',
                        function ($http, anfitrionesContext) {
                            return $http.get(anfitrionesContext);
                        }],

                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'anfitrion.list.html',
                        controller: 'anfitrionListCtrl',
                        controllerAs: 'ctrl',
                    },
                },
            }).state('anfitrionCreate', {
                url: '/signUp/anfitrion',

                resolve: {
                    anfitriones: ['$http', function ($http) {
                            return $http.get('data/anfitriones.json');
                        }],
                },
                views: {

                    'mainView': {

                        templateUrl: basePath + 'anfitrion.create.html',
                        controller: 'createAnfitrionCtrl'
                    },
                },
            });
            
            $stateProvider.state('anfitrionDetail', {
                url: '/{anfitrionId:int}',
                parent: 'anfitriones',

                param: {
                    anfitrionId: null,
                },
                resolve: {
                    anfitrionPresente: [function () {
                            return presente;
                        }]
                    ,
                    anfitrionActual: ['$http', 'anfitrionesContext',
                        '$stateParams', function ($http, anfitrionesContext
                                , $params) {


                            return $http.get(anfitrionesContext + '/' +
                                    ($params.anfitrionId));


                        }], anfitrionSiguiente: ['$http', 'anfitrionesContext',
                        '$stateParams',
                        function ($http, anfitrionesContext, $params) {

                            if ($params.anfitrionId + 1 !== 16) {
                                return $http.get(anfitrionesContext + '/'
                                        + ($params.anfitrionId + 1));
                            } else {
                                return $http.get(anfitrionesContext + '/'
                                        + ($params.anfitrionId));
                            }

                        }], anfitrionAnterior: ['$http', 'anfitrionesContext',
                        '$stateParams',
                        function ($http, anfitrionesContext, $params) {

                            if ($params.anfitrionId - 1 !== 0) {
                                return $http.get(anfitrionesContext + '/'
                                        + ($params.anfitrionId - 1));
                            } else {
                                return $http.get(anfitrionesContext + '/'
                                        + ($params.anfitrionId));
                            }

                        }], currentAnfitrion: ['$http', 'anfitrionesContext'
                                , '$stateParams',
                        function ($http, anfitrionesContext, $params) {

                            return $http.get(anfitrionesContext + '/'
                                    + $params.anfitrionId);

                        }]},
               
                views: {
                    'anfitrionCarouselView': {
                        templateUrl: basePath + 'anfitrion.carousel.html',
                        controller: ['$scope', 'anfitrionAnterior', 'anfitrionSiguiente',
                            'anfitrionActual', 'anfitriones', 'anfitrionPresente',
                            function ($scope, anfitrionAnterior, anfitrionSiguiente
                                    , anfitrionActual, anfitriones, anfitrionPresente) {


                                $scope.anfitrionPresente = anfitrionPresente;
                                $scope.anfitrionesRecords = anfitriones.data;
                                $scope.anfitrionActual = anfitrionActual.data;
                                $scope.anfitrionSiguiente = anfitrionSiguiente.data;
                                $scope.anfitrionAnterior = anfitrionAnterior.data;

                            }],

                    },
                    'detailView': {
                        templateUrl: basePath + 'anfitrion.detail.html',
                        controller: ['$state','$scope','$http', 'currentAnfitrion', '$stateParams',
                            function ($state,$scope, $http ,currentAnfitrion, $params) {
                                console.log($params.anfitrionId);
                                const object={};
                                $scope.delete =function(){
                                     $http.delete('api/anfitriones'+'/'+$params.anfitrionId, object).then(function(){
                                       $state.go('anfitrionesList',{},{reload:true});  
                                     });   
                                     
                                     
                                }


                                $scope.currentAnfitrion = currentAnfitrion.data;

                            }],
                    },

                },
            });


            $stateProvider.state('anfitrionReservasList', {
                url: '/reservas',
                parent: 'anfitrionDetail',

                param: {
                    anfitrionId: null
                },
                views: {
                    'ListasAnfitrionView': {
                        templateUrl: basePath + 'anfitrion.reservas.list.html',
                        controller: ['$scope', '$stateParams',
                            function ($scope, $params) {
                                $scope.currentAnfitrion =
                                        $scope.anfitrionesRecords[$params.anfitrionId - 1];
                            }],
                    }
                }
            });
            $stateProvider.state('anfitrionUpdate', {
                url: '/edit',
                parent: 'anfitrionDetail',

                param: {
                    anfitrionId: null,
                },
                views: {
                    'ListasAnfitrionView': {
                        templateUrl: basePath + 'anfitrion.update.html',
                        controller: ['$scope', '$stateParams',
                            function ($scope, $params) {
                                $scope.currentAnfitrion =
                                        $scope.anfitrionesRecords[$params.anfitrionId - 1];
                            }],

                    }
                },
            });
            $stateProvider.state('anfitrionViviendasList', {
                url: '/viviendas',
                parent: 'anfitrionDetail',

                param: {
                    anfitrionId: null,
                },
                views: {
                    'ListasAnfitrionView': {
                        templateUrl: basePath + 'anfitrion.viviendas.list.html',
                        controller: ['$scope', '$stateParams',
                            function ($scope, $params) {
                                $scope.currentAnfitrion =
                                        $scope.anfitrionesRecords[$params.anfitrionId - 1];
                            }],

                    },
                }
            });
        }
    ]);
})(window.angular);