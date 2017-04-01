(function (ng) {
    var mod = ng.module("pagoModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/Pago/';
            $urlRouterProvider.otherwise("/pagosList");
            $stateProvider.state('pagos', {
                url: '/pagos',
                abstract: true,
                resolve: {
                    pagos: ['$http', function ($http) {
                            return $http.get('data/pagos.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pago.html',
                        controller: ['$scope', 'pagos', function ($scope, pagos) {
                                $scope.pagosRecords = pagos.data;
                            }]
                    }
                }
            }).state('pagosList',{
                url: '/list',
                parent: 'pagos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'pago.list.html'
                        
                    }
                }
            });
        }]
    );
}
)(window.angular);
