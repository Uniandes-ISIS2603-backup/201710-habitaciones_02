(function(ng) {
    var mod = ng.module("viviendaModule");
    
    mod.controller("viviendaListCtrl", ['$scope','viviendas',
        function($scope,viviendas) {
            $scope.viviendasRecords = viviendas.data;
        }
    ]);
})(window.angular);