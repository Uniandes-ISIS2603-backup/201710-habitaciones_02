(function (ng) {
    var mod = ng.module("viajeroModule");

    mod.controller("viajeroCreateCtrl", ['$scope', '$http', 
        function ($scope, $http) 
        {
            $scope.viajero = {};
            $scope.signup = function () {
                $http.post('api/viajeros', $scope.viajero)
                        .then(function (data) {
                    console.log(data);
                });
            };
        }]
    );
    
    mod.controller("viajeroUpdateCtrl", ['$scope', '$http',
        function($scope, $http){
            
            $scope.viajero = {};
            
        }
    ]);

    mod.controller("viajeroListCtrl", ['$scope', 'viajeros',
        function ($scope, viajeros)
        {
            $scope.RecordsViajero = viajeros.data;
        }
    ]);

})(window.angular);