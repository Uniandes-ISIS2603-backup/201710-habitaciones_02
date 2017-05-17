(function (ng) {
    var mod = ng.module("habitacionModule");

    mod.controller("habitacionListCtrl", ['$scope', 'habitaciones',
        function ($scope, habitaciones) {
            $scope.habitacionesRecords = habitaciones.data;
        }
    ]);
    
    mod.controller("habitacionCreateCtrl", ['$scope', '$http', 
        function ($scope, $http) 
        {
            
            $scope.habitacion = {};
            $scope.crear = function () {
                
                $http.post('api/habitaciones', $scope.habitacion)
                    .then(function (data) {
                        
                        console.log(data);
                    });
            };
        }]
    );

})(window.angular);