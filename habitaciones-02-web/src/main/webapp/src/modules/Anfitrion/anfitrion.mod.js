(function (ng) {
    // Definición del módulo
    var mod = ng.module("anfitrionModule", ['ui.router']);

   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Anfitrion/';
            // Definición del estado 'anfitrionesList' donde se listan los anfitriones
            $stateProvider.state('anfitrionesList', {
                // Url que aparecerá en el browser
                url: '/anfitriones/list',
                // Se define una variable anfitriones (del estado) que toma por valor 
                // la colección de anfitriones que obtiene utilizando $http.get 
                templateUrl: basePath+'anfitrion.list.html',
                controller: 'anfitrionListCtrl',
                controllerAs: 'ctrl',
                resolve: {
                    anfitriones: ['$http', function ($http) {
                            return $http.get('data/anfitriones.json'); 
                        }]
                }
            });
        }
    ]);
})(window.angular);