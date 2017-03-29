(function (ng) { 
    // Definición del módulo
    var mod = ng.module("viajeroModule", ['ui.router']);
    
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Viajero/';
            // Mostrar la lista de viajeros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/viajerosList");
            
            // Definición del estado 'ViajerosList' donde se listan los libros
            $stateProvider.state('viajerosList', 
            {
                // Url que aparecerá en el browser
                url: '/viajeros/list',
                // Se define una variable viajeros (del estado) que toma por valor 
                // la colección de libros que obtiene utilizando $http.get 
                 resolve: 
                {
                    viajeros: ['$http', function ($http) 
                    {
                        return $http.get('data/viajeros.json'); // $http retorna un apromesa que aquí no se está manejando si viene con error.
                    }]
                },
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'viajero.list.html',
                // El controlador guarda en el scope en la variable booksRecords los datos que trajo el resolve
                // booksRecords será visible en el template
                controller: ['$scope', 'viajeros', function ($scope, viajeros) {
                        $scope.RecordsViajero = viajeros.data;
                    }]              
            });
        }
    ]);
})(window.angular);