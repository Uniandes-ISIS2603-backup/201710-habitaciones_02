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
            
            
            $stateProvider.state('login', {
                url: '/login',

                resolve: {
                    viajeros: ['$http', 'viajeroContext', 
                        function ($http, viajeroContext) {
                            return $http.get(viajeroContext);
                        }],
                    anfitriones: ['$http', function ($http) {
                            return $http.get('api/anfitriones');
                        }]
                },
                views: {

                    'mainView': {

                        templateUrl: 'src/modules/login.html',
                        controller: ['$scope', 'anfitriones', 'viajeros', '$http', '$state',
                            function ($scope, anfitriones, viajeros, $http, $state) {
                                $scope.RecordsViajero = viajeros.data;
                                $scope.anfitrionesRecords = anfitriones.data;
                                
                                $scope.usuario = {};
                                
                                $scope.login = function (){
                                  
                                    $http.get('api/viajeros/loginViajero?correoE='
                                            + $scope.usuario.correo +'&contrasena='+$scope.usuario.contrasena)
                                    .then(function(resultado){
                                            console.log('Econtro viajero!');
                                            $state.go('viajeroDetail',{viajeroId: resultado.data.idUsuario});
                                            console.log(resultado);
                                    }).catch (function(error){
                                        document.getElementById('errorLogin').innerHTML = error.data;
                                        console.log('se genero este error: ' + error.data);
                                    });


                                };
                            }]
                    }
                }
            });
            
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
                
                resolve:{
                    listaReservas:['$http', 'viajeroContext', '$stateParams', 
                        function ($http, viajeroContext, $params) 
                        {
                            return $http.get(viajeroContext + '/'
                                    + $params.viajeroId + '/' + 'reservas');
                        }
                    ]
                },
                param: {
                    viajeroId: null
                },
                views: {
                        'ListasViajeroView':{
                        templateUrl: basePath + 'viajero.reservas.list.html',
                        
                        controller: 'listReservControl'

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
                        templateUrl: basePath + 'viajero.update.html',
                        controller:'viajeroUpdateCtrl'
                        //hereda el currentViajero del detailViajero?
                        
                    }
                } 
            });  
        }
    ]);
})(window.angular);