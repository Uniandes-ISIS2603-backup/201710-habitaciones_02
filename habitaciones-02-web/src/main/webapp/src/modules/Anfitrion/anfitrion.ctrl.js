(function(ng) {
    var mod = ng.module("anfitrionModule");
    mod.controller("anfitrionListCtrl", ['$scope','anfitriones',
        function($scope,anfitriones) {
            $scope.anfitrionesRecords = anfitriones.data;
        }
    ]);
    mod.controller("anfitrionCarouselCtrl",['$scope','anfitriones','anfitrionActual', 'anfitrionSiguiente','anfitrionAnterior', 'anfitrionPresente',
        function($scope,anfitriones,anfitrionActual, anfitrionSiguiente, anfitrionAnterior, anfitrionPresente){
            $scope.anfitrionPresente = anfitrionPresente;
            $scope.anfitrionesRecords = anfitriones.data;
            $scope.anfitrionActual=anfitrionActual;
            $scope.anfitrionAnterior=anfitrionAnterior;
            $scope.anfitrionSiguiente=anfitrionSiguiente;
           
                    }]);
})(window.angular);