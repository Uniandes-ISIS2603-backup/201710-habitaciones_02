(function (ng) {
    var mod = ng.module("pagoModule", ['ui.router']);
    mod.constant("pagosContext", "api/pagos");
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/Pago/';
            $stateProvider.state('pagos', {
                url: '/pagos',
                abstract: true,
                resolve: {
                    pagos: ['$http','pagosContext', 
                        function ($http, pagosContext) {
                            return $http.get(pagosContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pago.html',
                        controller: ['$scope', 'pagos', 
                                    function ($scope, pagos) {
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
            }).state('pagoDetail',{
                url: '/{pagoId:int}/detail',
                parent:'pagos',
                param:{
                    pagoId: null
                },
                resolve:{
                    currentPago:['$http','$stateParams','pagosContext',
                    function($http, $params,pagosContext){
                        return $http.get(pagosContext + '/' + $params.pagoId);
                    }]
                },
                views:{
                    'detailView':{
                        templateUrl:basePath + 'pago.detail.html',
                        controller:['$scope','currentPago',
                                    function($scope,currentPago){
                                        $scope.currentPago = currentPago.data;
                                    }]
                    },
                    'listView':{
                        templateUrl: basePath + 'pago.list.html'
                    }
                }
            });
        }]
    );
}
)(window.angular);
