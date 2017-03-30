(function (ng) {
    // Definición del módulo
    var mod = ng.module("resenaModule", ['ui.router']);
    
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Resena/';
            // Mostrar la lista de resenas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/resenasList");
            
            // Definición del estado 'resenasList' donde se listan los libros
            $stateProvider.state('resenasList', 
            {
                // Url que aparecerá en el browser
                url: '/resenas/list',
                // Se define una variable resenas (del estado) que toma por valor 
                // la colección de libros que obtiene utilizando $http.get 
                 
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'resena.list.html',
                // El controlador guarda en el scope en la variable booksRecords los datos que trajo el resolve
                // booksRecords será visible en el template
                controller: 'resenaListCtrl',
                controllerAs: 'ctrl',
                resolve: 
                {
                    resenas: ['$http', function ($http) 
                    {
                        return $http.get('data/resenas.json'); // $http retorna un apromesa que aquí no se está manejando si viene con error.
                    }]
                }
            });
        }
    ]);
})(window.angular);