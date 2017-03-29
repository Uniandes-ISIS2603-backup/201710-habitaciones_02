(function (ng) {
    var mod = ng.module("viviendaModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Vivienda/';
            $urlRouterProvider.otherwise("/viviendasList");
            $stateProvider.state('viviendasList', {
                url: '/books/list',
                resolve: {
                    viviendas: ['$http', function ($http) {
                            return $http.get('data/viviendas.json');
                        }]
                },
                templateUrl: basePath + 'vivienda.list.html',
                controller: ['$scope', 'viviendas', function ($scope, viviendas) {
                        $scope.viviendasRecords = viviendas.data;
                    }]

            });
        }
    ]);
})(window.angular);