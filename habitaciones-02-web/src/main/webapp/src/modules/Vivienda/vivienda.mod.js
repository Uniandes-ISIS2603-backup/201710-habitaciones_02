(function (ng) {
    var mod = ng.module("viviendaModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Vivienda/';
            $stateProvider.state('viviendasList', {
                url: '/viviendas/list',
                resolve: {
                    viviendas: ['$http', function ($http) {
                            return $http.get('data/viviendas.json');
                        }]
                },
                templateUrl: basePath + 'vivienda.list.html',
                controller: 'viviendaListCtrl',
                controllerAs: 'ctrl'

            });
        }
    ]);
})(window.angular);