(function (ng) { 
    // Definición del módulo
    var mod = ng.module("viajeroModule", ['ui.router']);
        mod.constant("viajeroContext","api/viajeros");
        
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) 
        {
            
        // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Viajero/';
        // Mostrar la lista de viajeros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/viajerosList");
            
            $stateProvider.state('viajero', {
                
                url: '/viajeros',
                abstract: true,
                resolve: {
                    viajeros: ['$http', 'viajeroContext', 
                        function ($http, viajeroContext)
                        {
                            return $http.get(viajeroContext);
                        }
                    ]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'viajero.html',
                        controller: 'viajeroListCtrl'
                    }
                }
                
            }).state('viajerosList',  {
                // Url que aparecerá en el browser
                url: '/list',
                parent: 'viajero',
                
                views:{
                    'listView': {
                        templateUrl: basePath + 'viajero.list.html'
                    }
                }
               
            }).state('viajeroDetail', {
                url: '/{viajeroId:int}',
                parent: 'viajero',
                param: {
                    viajeroId: null
                },
                resolve:{
                    currentViajero:['$http', 'viajeroContext', '$stateParams', 
                        function ($http, viajeroContext, $params) 
                        {
                            return $http.get(viajeroContext + '/'
                                    + $params.viajeroId);
                        }
                    ]
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'viajero.list.html',
                        controller: ['$scope', 'viajeros', 
                            function ($scope, viajeros)
                            {
                                $scope.RecordsViajero = viajeros.data;
                            }
                        ]
                    },
                    'detailView': {
                        templateUrl: basePath + 'viajero.detail.html',
                        controller: ['$scope', 'currentViajero', 
                            function ($scope, currentViajero)
                            {
                                $scope.currentViajero = currentViajero.data;
                            }
                        ]
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
                        templateUrl: basePath + 'viajero.reservas.list.html'
                        //hereda el currentViajero del viajeroDetail??
                        
                        //Yo puedo llamar las reservas del viajero 
                        //desde HTML en vez de en el controlador?
                    }
                } 
            });
            
            
            $stateProvider.state('viajeroCreate', {
                url: '/signUpViajero',
                parent: 'viajero',
                
                views: {
                    
                    'detailView': {
                       
                        templateUrl: basePath + 'viajero.create.html',
                        controller: 'viajeroCreateCtrl'
                        //necesita a los viajeros? creo que no
                        // si fuesen necesarios, sería mejor h
                    }
                } 
            });
            $stateProvider.state('viajeroUpdate', {
                url: '/update',
                parent: 'viajeroDetail',
                
                views: {
                    'ListasViajeroView':{
                        templateUrl: basePath + 'viajero.update.html'
                        
                        //hereda el currentViajero del detailViajero?
                        
                    }
                } 
            });  
        }
    ]);
})(window.angular);