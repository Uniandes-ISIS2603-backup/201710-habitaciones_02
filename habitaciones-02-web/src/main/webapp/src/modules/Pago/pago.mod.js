(function (ng) {
    // Definición del módulo
    var mod = ng.module("pagoModule", ['ui.router']);
 
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/pagos/';
            // Mostrar la lista de libros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/pagosList");
            // Definición del estado 'pagosList' donde se listan los libros
            $stateProvider.state('pagosList', {
                // Url que aparecerá en el browser
                url: '/pagos/list',
                // Se define una variable pagos (del estado) que toma por valor 
                // la colección de libros que obtiene utilizando $http.get 
                 resolve: {
                    pagos: ['$http', function ($http) {
                            return $http.get('data/pagos.json'); // $http retorna un apromesa que aquí no se está manejando si viene con error.
                        }]
                },
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'pagos.list.html',
                // El controlador guarda en el scope en la variable pagosRecords los datos que trajo el resolve
                // pagosRecords será visible en el template
                controller: ['$scope', 'pagos', function ($scope, pagos) {
                        $scope.pagosRecords = pagos.data;
                    }]              
            });
        }
    ]);
})(window.angular);
