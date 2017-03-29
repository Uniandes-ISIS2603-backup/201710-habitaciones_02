(function(ng) {
    var mod = ng.module("anfitrionModule");
    
    mod.controller("anfitrionListCtrl", ['$scope','anfitriones',
        function($scope,anfitriones) {
            $scope.anfitrionesRecords = anfitriones.data;
        }
    ]);
})(window.angular);