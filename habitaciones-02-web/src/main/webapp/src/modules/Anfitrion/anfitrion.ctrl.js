(function (ng) {
    var mod = ng.module("anfitrionModule");
    mod.controller("createAnfitrionCtrl", ['$scope', '$http', 
        function ($scope, $http) {
            $scope.anfitrion = {};
            $scope.signup = function () {
                $http.post('api/anfitriones', $scope.anfitrion)
                        .then(function (data) {
                    console.log(data);
                });
            }
        }]
            );
    mod.controller("anfitrionListCtrl", ['$scope', 'anfitriones',
        function ($scope,  anfitriones) {
            
            $scope.anfitrionesRecords = anfitriones.data;
        }

    ]);
    
    
})(window.angular);