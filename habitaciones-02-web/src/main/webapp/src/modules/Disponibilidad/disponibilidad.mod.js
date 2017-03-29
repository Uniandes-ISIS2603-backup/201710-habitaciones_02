(function (ng) {
    // Definición del módulo
    var mod = ng.module("disponibilidadModule", ['ui.router']);
 
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Disponibilidad/';
            // Mostrar la lista de dispponibilidades será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/disponibilidadesList");
            // Definición del estado 'disponibilidadesList' donde se listan las disponibilidades
            $stateProvider.state('disponibilidadesList', {
                // Url que aparecerá en el browser
                url: '/disponibilidades/list',
                // Se define una variable disponibilidades (del estado) que toma por valor 
                // la colección de disponibilidades que obtiene utilizando $http.get 
                 resolve: {
                    disponibilidades: ['$http', function ($http) {
                            return $http.get('data/disponibilidades.json'); // $http retorna una promesa que aquí no se está manejando si viene con error.
                        }]
                },
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'disponibilidad.list.html',
                // El controlador guarda en el scope en la variable disponibilidadesRecords los datos que trajo el resolve
                // disponibilidadesRecords será visible en el template
                controller: ['$scope', 'disponibilidades', function ($scope, disponibilidades) {
                        $scope.disponibilidadesRecords = disponibilidades.data;
                    }]              
            });
        }
    ]);
})(window.angular);
