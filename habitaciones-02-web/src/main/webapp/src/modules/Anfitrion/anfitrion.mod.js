(function (ng) {
    // Definición del módulo
    var mod = ng.module("anfitrionModule", ['ui.router']);
 
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Anfitrion/anfitrion.list.html';
            // Mostrar la lista de libros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/anfitrionesList");
            // Definición del estado 'anfitrionesList' donde se listan los libros
            $stateProvider.state('anfitrionesList', {
                // Url que aparecerá en el browser
                url: '/anfitriones/list',
                // Se define una variable books (del estado) que toma por valor 
                // la colección de libros que obtiene utilizando $http.get 
                 resolve: {
                    anfitriones: ['$http', function ($http) {
                            return $http.get('data/anfitriones.json'); // $http retorna un apromesa que aquí no se está manejando si viene con error.
                        }]
                },
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath ,
                // El controlador guarda en el scope en la variable anfitrionesRecords los datos que trajo el resolve
                // booksRecords será visible en el template
                controller: ['$scope', 'anfitriones', function ($scope, anfitriones) {
                        $scope.anfitrionesRecords = anfitriones.data;
                            
                    }]              
            });
        }
    ]);
})(window.angular);