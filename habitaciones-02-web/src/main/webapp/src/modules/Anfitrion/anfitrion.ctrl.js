(function(ng) {
    var mod = ng.module("anfitrionModule");
    mod.controller("anfitrionListCtrl", ['$scope','anfitriones',
        function($scope,anfitriones) {
            $scope.anfitrionesRecords = anfitriones.data;
        }
    ]);
    mod.controller("anfitrionCarouselCtrl",['$scope','anfitriones','$stateParams', 'anfitrionPresente',
        function($scope,anfitriones,$params, anfitrionPresente){
            $scope.anfitrionPresente = anfitrionPresente;
            $scope.anfitrionesRecords = anfitriones.data;
            
                                $scope.anfitrionActual = $scope.anfitrionesRecords[$params.anfitrionId-1];
                                $scope.anfitrionSiguiente = $scope.anfitrionesRecords[$params.anfitrionId];
                                $scope.anfitrionAnterior = $scope.anfitrionesRecords[$params.anfitrionId-2];
           
                    }]);
})(window.angular);