(function(ng) {
    var mod = ng.module("anfitrionModule");
    mod.controller("anfitrionListCtrl", ['$scope','anfitriones',
        function($scope,anfitriones) {
            $scope.anfitrionesRecords = anfitriones.data;
        }
    ]);
    mod.controller("anfitrionCarouselCtrl",['$http','anfitrionesContext','$scope','anfitriones','$stateParams', 'anfitrionPresente',
        function($http,$scope,anfitrionesContext,anfitriones,$params, anfitrionPresente){
            $scope.anfitrionPresente = anfitrionPresente;
            $scope.anfitrionesRecords = anfitriones.data;
            
                                $scope.anfitrionActual = $http.get(anfitrionesContext+'/'+($params.anfitrionId));
                                $scope.anfitrionSiguiente = $http.get(anfitrionesContext+'/'+($params.anfitrionId+1));
                                $scope.anfitrionAnterior = $http.get(anfitrionesContext+'/'+($params.anfitrionId-1)); 
           
                    }]);
})(window.angular);