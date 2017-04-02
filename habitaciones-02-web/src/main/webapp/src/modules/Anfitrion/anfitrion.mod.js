(function (ng) {
    // Definición del módulo
    var mod = ng.module("anfitrionModule", ['ui.router']);

   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Anfitrion/';
            var actual=1;
            var presente=0;
            var anterior;
            var siguiente;
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
                        controller: 'anfitrionListCtrl'
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
                    'mainView': {
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
                    anfitrionId: null,        
                },
                resolve: {
                anfitrionPresente: [function(){
                        return presente;
                }]
                ,
                anfitrionActual: ['$stateParams', function ( $params) {
                                console.log($params.anfitrionId);
                                
                                
                                return $params.anfitrionId-1;
                         
                        
                }], anfitrionSiguiente: [ '$stateParams', function ( $params) {
                                console.log($params.anfitrionId);
                                
                                
                                return $params.anfitrionId;
                         
                        
                }], anfitrionAnterior: ['$stateParams', function ( $params) {
                                console.log($params.anfitrionId);
                                
                                
                                return $params.anfitrionId-2;
                         
                        
                }]},
        
                views: {
                    'anfitrionCarouselView': {
                        templateUrl: basePath + 'anfitrion.carousel.html',
                        controller: 'anfitrionCarouselCtrl'
                        
                    },
                    'detailView': {
                        templateUrl: basePath + 'anfitrion.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                console.log($params.anfitrionId);
                                
                                
                                actual=$params.anfitrionId-1;
                                $scope.currentAnfitrion = $scope.anfitrionesRecords[$params.anfitrionId-1];
                            }]
                    }

                } 
            });
        }
    ]);
})(window.angular);