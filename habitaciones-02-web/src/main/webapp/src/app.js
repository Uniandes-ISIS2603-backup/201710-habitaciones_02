(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        // Internal modules dependencies       

        'viajeroModule',
        'resenaModule',
        'habitacionModule',
        'disponibilidadModule',
        'reservaModule',
        'viviendaModule',
        'pagoModule',
        'anfitrionModule'



    ]);
    // Resuelve problemas de las promesas
    app.config(['$stateProvider', '$qProvider', function ($stateProvider, $qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
            $stateProvider.state('startPage', {
                url: '',
                views: {
                    'mainView': {
                        templateUrl: 'src/' + "start.html"
                    }
                }
            });
        }]);

    app.controller("indexCtrl", ['$scope', '$state',
        function ($scope, $state) {
            $scope.refresh = function(){
                $state.go('startPage',{},{reload: true});
            };
        }
    ]);

})(window.angular);
