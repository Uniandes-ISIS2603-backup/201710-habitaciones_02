(function (ng)
{
    var mod = ng.module("habitacionModule");

    mod.controller("habitacionListCtrl", ['$scope', 'habitaciones',
        function ($scope, habitaciones)
        {
            $scope.habitacionesRecords = habitaciones.data;
        }
    ]);

    mod.controller("habitacionCreateCtrl", ['$scope', '$http', '$state',
        function ($scope, $http, $state)
        {

            $scope.habitacion = {};
            $scope.crear = function ()
            {

                $http.post('api/habitaciones', $scope.habitacion)
                        .then(function (data)
                        {
                            $state.go('habitacionDetail', {habitacionId: data.data.id});
                            console.log(data);
                        });
            };
        }]
            );
    mod.controller("habitacionUpdateCtrl", ['$scope', '$http', '$stateParams', '$state',
        function ($scope, $http, $stateParams, $state)
        {

            $scope.habitacion = {};

            $scope.actualizar = function ()
            {


                $http.put('api/habitaciones/' + $stateParams.id, $scope.currentHabitacion)
                        .then(function (data)
                        {
                            $state.go('habitacionDetail', {habitacionId: $stateParams.id});
                            console.log(data);
                        }).catch(function (error)
                {
                    document.getElementById('errorUpdate').innerHTML = error.data;
                    console.log('se genero este error: ' + error.data);
                });
            }
        }
    ]);
    
    mod.controller("habitacionDeleteCtrl", ['$scope', 'currentHabitacion', '$http', '$stateParams', '$state',
        function ($scope, currentHabitacion, $http, $stateParams, $state) 
        {
            $scope.currentHabitacion = currentHabitacion.data; 
            
            $scope.delete = function(){
                
                $http.delete('api/habitaciones/'+ $stateParams.id)
                    .then(function(data){
                        $state.go('habitacionesList');
                        console.log(data);
                    });
            };

        }]
    );

})(window.angular);