(function (ng) {
    // Definición del módulo
    var mod = ng.module("reservaModule", ['ui.router']);
 
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Reserva/';
            // Mostrar la lista de libros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/reservasList");
            // Definición del estado 'booksList' donde se listan los libros
            $stateProvider.state('reservasList', {
                // Url que aparecerá en el browser
                url: '/reservas/list',
                // Se define una variable reservas (del estado) que toma por valor  para el json
                // la colección de libros que obtiene utilizando $http.get 
                 resolve: {
                    reservas: ['$http', function ($http) {
                            return $http.get('data/reserva.json'); // $http retorna un apromesa que aquí no se está manejando si viene con error.
                        }]
                },
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'reserva.list.html',
                // El controlador guarda en el scope en la variable reservaRecords los datos que trajo el resolve
                // reservaRecords será visible en el template
                controller: ['$scope', 'reservas', function ($scope, reservas) {
                        $scope.reservasRecords = reservas.data;
                    }]              
            });
        }
    ]);
})(window.angular);
