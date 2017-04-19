(function (ng)
{
    // Definición del módulo
    var mod = ng.module("disponibilidadModule", ['ui.router']);
    mod.constant("disponibilidadContext", "/disponibilidades");

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider)
        {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Disponibilidad/';
            // Mostrar la lista de dispponibilidades será el estado por defecto del módulo
            //$urlRouterProvider.otherwise("/disponibilidadesList");
            // Definición del estado 'disponibilidadesList' donde se listan las disponibilidades
            $stateProvider.state('disponibilidadDetail', {
                // Url que aparecerá en el browser
                url: '/disponibilidades/{disponibilidadId:int}/detail',
                
                parent: 'habitacionDetail',

                resolve: {
                    disponibilidades: ['$http', 'disponibilidadContext', '$stateParams', function ($http, disponibilidadContext, $params)
                        {
                            return $http.get('api/habitaciones/' + $params.habitacionId + disponibilidadContext); // $http retorna una promesa que aquí no se está manejando si viene con error.
                        }]
                },
                param: {
                    disponibilidadId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'disponibilidad.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params)
                            {
                                $scope.currentDisponibilidad = $scope.disponibilidadesRecords[$params.disponibilidadId - 1];
                            }]
                    }

                }
            });

        }
    ]);
})(window.angular);
