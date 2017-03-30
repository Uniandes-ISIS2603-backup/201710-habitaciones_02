(function (ng) {
    // Definición del módulo
    var mod = ng.module("anfitrionModule", ['ui.router']);

   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Anfitrion/';
            // Definición del estado 'anfitrionesList' donde se listan los anfitriones
            $stateProvider.state('anfitrion', {
                url: '/anfitriones',
                abstract: true,
                resolve: {
                    anfitriones: ['$http', function ($http) {
                            return $http.get('data/anfitriones.json');
                        }]
                },
                views: {
                    'mainView': {
                        
                       
                        templateUrl: basePath + 'anfitrion.html',
                        controller: ['$scope', 'anfitriones', function ($scope, anfitriones) {
                                $scope.anfitrionesRecords = anfitriones.data;
                            }]
                    }
                }
            }).state('anfitrionesList', {
                // Url que aparecerá en el browser
                url: '/anfitriones/list',
                // Se define una variable anfitriones (del estado) que toma por valor 
                // la colección de anfitriones que obtiene utilizando $http.get 
                
                resolve: {
                    anfitriones: ['$http', function ($http) {
                            return $http.get('data/anfitriones.json'); 
                        }]
                },
                views:{
                    mainView: {
                        templateUrl: basePath+'anfitrion.list.html',
                         controller: 'anfitrionListCtrl',
                         controllerAs: 'ctrl',
                    }
                }
            });
            $stateProvider.state('anfitrionDetail', {
                 url: '/{anfitrionId:int}/detail',
                parent: 'anfitrion',
                param: {
                    anfitrionId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'anfitrion.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'anfitrion.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentAnfitrion = $scope.anfitrionesRecords[$params.anfitrionId-1];
                            }]
                    }

                } 
            });
        }
    ]);
})(window.angular);