(function (ng) {
    // Definición del módulo
    var mod = ng.module("habitacionModule", ['ui.router']);
 
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Habitacion/';
            // Mostrar la lista de habitaciones será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/habitacionesList");
            // Definición del estado 'habitacionesList' donde se listan las habitaciones
            $stateProvider.state('habitacionesList', {
                // Url que aparecerá en el browser
                url: '/habitaciones/list',
                // Se define una variable habitaciones (del estado) que toma por valor 
                // la colección de habitaciones que obtiene utilizando $http.get 
                 resolve: {
                    habitaciones: ['$http', function ($http) {
                            return $http.get('data/habitaciones.json'); // $http retorna una promesa que aquí no se está manejando si viene con error.
                        }]
                },
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'habitacion.list.html',
                // El controlador guarda en el scope en la variable habitacionesRecords los datos que trajo el resolve
                // habitacionesRecords será visible en el template
                controller: ['$scope', 'habitaciones', function ($scope, habitaciones) {
                        $scope.habitacionesRecords = habitaciones.data;
                    }]              
            });
        }
    ]);
})(window.angular);
