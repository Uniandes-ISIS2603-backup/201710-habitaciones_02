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
                 url: '/{resenaId:int}/detail',
                parent: 'resena',
                param: {
                    resenaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'resena.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'resena.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentResena = $scope.RecordsResena[$params.resenaId-1];
                            }]
                    }

                } 
            });
            
        }
    ]);
})(window.angular);