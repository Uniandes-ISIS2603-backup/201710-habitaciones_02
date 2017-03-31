(function (ng) {
    // Definición del módulo
    var mod = ng.module("disponibilidadModule", ['ui.router']);
 
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
                    disponibilidades: ['$http', function ($http) {
                            console.log("hi");
                            return $http.get('data/disponibilidades.json'); // $http retorna una promesa que aquí no se está manejando si viene con error.
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'disponibilidad.html',
                        controller: ['$scope', 'disponibilidades', function ($scope, disponibilidades) {
                                $scope.disponibilidadesRecords = disponibilidades.data;
                            }]
                    }
                }
                }).state('disponibilidadesList', {
                url: '/list',
                parent: 'disponibilidades',
                views: {
                    'listView': {
                        templateUrl: basePath + 'disponibilidad.list.html'
                    }
                }
                             
            });
            
            
            
        }
    ]);
})(window.angular);
