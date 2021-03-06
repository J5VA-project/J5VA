app.controller("category-manager-ctrl", function($scope, $http){
	$("#btn-add").click(function () {
		$('.error_name').css('color','red');
		$('.error_name').css('font-style','italic');
		$('.error_description').css('color','red');
		$('.error_description').css('font-style','italic');
	
		
		var check = true;
		if ($('#name').val() == '') {
			$('.error_name').html('X Category name not null');
			$('.error_name').show();
			check = false;
		} else {
			$('.error_name').hide();
		}

		if ($('#description').val() == '') {
			$('.error_description').html('X Category Description not null');
			$('.error_description').show();
			check = false;
		} else {
			$('.error_description').hide();
		}

		if(check==true){
			$scope.create();
		}
	});

   $scope.categories = [];
	$scope.form = {};

	$scope.initialize = function() {
		//load products
		$http.get("/rest/category").then(resp => {
			$scope.categories = resp.data;
		});
	}

	//khởi đầu
	$scope.initialize();


	//xóa form
	$scope.reset = function() {
		$scope.form = {
		};
	}

	//hiển thị lên form
	$scope.edit = function(category) {
		$scope.form = angular.copy(category);
	}

	//thêm sản phẩm mới
	$scope.create = function() {
		var category = angular.copy($scope.form);
		$http.post(`/rest/category`, category).then(resp => {
			$scope.categories.push(resp.data);
			$scope.reset();
			alert("Thêm mới category thành công!");
		}).catch(error => {
			alert("Lỗi thêm mới category!");
			console.log("Error", error);
		});
	}

	//thêm sản phẩm mới
	$scope.update = function() {
		var category = angular.copy($scope.form);
		$http.put(`/rest/category/${category.foodcategory_id}`, category).then(resp => {
			var index = $scope.categories.findIndex(p => p.foodcategory_id == category.foodcategory_id);
			$scope.categories[index] = category;
			alert("Cập nhật category thành công!");
		}).catch(error => {
			alert("Lỗi cập nhật category!");
			console.log("Error", error);
		});
	}

	//xóa sản phẩm
	$scope.delete = function(category) {
		$http.delete(`/rest/category/${category.foodcategory_id}`).then(resp => {
			var index = $scope.categories.findIndex(p => p.foodcategory_id == category.foodcategory_id);
			$scope.categories.splice(index, 1);
			$scope.reset();
			alert("Xóa category thành công!");
		}).catch(error => {
			alert("Lỗi xóa category!");
			console.log("Error", error);
		});
	}

	$scope.pager = {
		page: 0,
		category: 6,
		get categories() {
			var start = this.page * this.category;
			return $scope.categories.slice(start, start + this.category);
		},
		get count() {
			return Math.ceil(1.0 * $scope.categories.length / this.category);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}
});