$(function(){
	$.ajaxSetup({
		dataType:'json',
		error(){
			console.log('请求失败')
		}
	})
	let ajax = {
		// 得到所有产品
		getAllProducts(){
			const url = '/tea/getTeaDetailed_all';
			const promise = new Promise((resolve,reject)=>{
				$.ajax({
					url,
					success(data){
						if(data.code == 1){
							let str = '';
							let state
							for(let i=0;i<data.data.length;i++){
								state = '下架';
								if(data.data[i].tea_state == 2){
									state = '上架';
								}
								str += `<tr data-teaId="${data.data[i].tea_Id}">
											<td><img src="${data.data[i].tea_photo}" class="products-img"></td>
											<td>${data.data[i].tea_id}</td>
											<td>${data.data[i].tea_name}</td>
											<td>${data.data[i].tea_intro}</td>
											<td>${data.data[i].tea_num}</td>
											<td>${data.data[i].tea_price}</td>
											<td>
												<button class="btn btn-info modefiedBtn" data-toggle="modal" data-target="#modefiedModal">修改</button>
												<button class="btn btn-warning updateBtn">${state}</button>
												<button class="btn btn-danger deleteBtn">删除</button>
											</td>
										</tr>`
								
							}
							$('#allProducts tbody').append(str)
							resolve('done');
						}
					}
				})
			})
			return promise;			
		},
		// 获取上架商品
		getShelvesProducts(){
			const url = '/tea/getTeaDetailedByState';
			$.ajax({
				url,
				success(data){
					if(data.code == 1){
						let str = '',
							str2=``;
						for(let i=0;i<data.data.length;i++){
							str += `<tr>
										<td><img src="${data.data[i].tea_photo}" class="products-img"></td>
										<td>${data.data[i].tea_id}</td>
										<td>${data.data[i].tea_name}</td>
										<td>${data.data[i].tea_intro}</td>
										<td>${data.data[i].tea_num}</td>
										<td>${data.data[i].tea_price}</td>
									</tr>`
							str2 += `<div class="col-sm-6 col-md-4" data-itemId="${data.data[i].tea_id}">
								    <div class="thumbnail">
								      <img src="${data.data[i].tea_photo}" class="center-block" style="width:200;height:150px">
								      <div class="caption">
								        <h3 style="font-size:24px;">${data.data[i].tea_name}</h3>
								        <p>${data.data[i].tea_intro}</p>
								        <p>${data.data[i].tea_price}￥</p>
								        <p>
								        	<button class="btn btn-primary addBtn" data-toggle="modal" data-target="#cartModal">加入购物车</button>
								        	<button class="btn btn-default viewBtn" data-toggle="modal" data-target="#viewModal">查看</button>
								        </p>
								      </div>
								    </div>
								  </div>`
						}
						$('#shelvesPro tbody').append(str)
						$('#itemWrap').append(str2);
					}
				}
			}).then(function(value){
				$('.addBtn').click(function(){
					// 加入购物车
					let $item = $(this).parents('.col-md-4');
					let teaId = $item.attr('data-itemId');	
					$('#orderNum').one('click',function(){
						let teaBuyNum = $('#cartModal').find('option:selected').val()
						ajax.addShopCart(teaBuyNum,teaId); 
					})
				})
				$('.viewBtn').click(function(){
					let teaId = $(this).parents('.col-md-4').attr('data-itemid');
					ajax.getItemInfo(teaId);
				})
			})
		},
		// 删除商品
		deleteTea(teaId){
			const url = '/tea/deleteTeaByID';
			const promise = new Promise((resolve,reject)=>{
				$.ajax({
					url,
					type:'post',
					data:{teaId},
					success(data){
						if(data.code == 1){
							alert('删除成功')
							resolve('done');
						}
					}
				})
			})
			return promise;
		},
		// 上架/下架
		updateTeaState(teaId,teaState){
			const url = '/tea/updateTeaState';
			const promise = new Promise((resolve,reject)=>{
				$.ajax({
					url,
					type:'post',
					data:{teaId,teaState},
					success(data){
						alert(data.msg);
						resolve('done');
					}
				})
			})
			return promise;
		},
		// 加入购物车
		addShopCart(teaBuyNum,teaId){
			const url = '/order/creatOrder';
			$.ajax({
				url,
				type:'post',
				data:{teaBuyNum,teaId},
				success(data){
					if(data.code == 1){
						alert(data.msg)
					}
				}
			})
		},
		// 获取单个商品信息
		getItemInfo(teaId){
			const url = '/tea/getTeaDetailedByID';
			$.ajax({
				url,
				data:{teaId},
				success(data){
					if(data.code == 1){
						$('#viewModal .modal-body').html(`<ul class="list-group">
												<li class="list-group-item text-center">${data.data[0].tea_name}</li>
												<li class="list-group-item"><img src="${data.data[0].tea_photo}" style="width: 400px;height: 350px;" class="center-block"></li>
												<li class="list-group-item"><p>${data.data[0].tea_intro}</p></li>
												<li class="list-group-item">${data.data[0].tea_price}￥</li>
											</ul>`)
					}
				}
			})
		}
	}
	let $modefiedTea;
	ajax.getAllProducts().then((value)=>{
		$('.deleteBtn').click(function(){
			let $item=$(this).parents('tr'),
				teaId=$item.find('td:eq(1)').html();
			ajax.deleteTea(teaId).then((value)=>{
				$item.remove();
			})
		})
		$('.modefiedBtn').click(function(){
			let $item=$(this).parents('tr');
			let $boxItem = $('#modefiedModal .form-group:eq(0)');
			$boxItem.find('input').val($item.find('td').eq(1).html())
			$modefiedTea = $item;
		})
		$('.updateBtn').click(function(){
			let $item = $(this).parents('tr');
			$updateTea = $item;
			let teaId = $item.find('td:eq(1)').html(),
				teaState;
			if($(this).html() == '上架'){
				teaState = 1;
			}else if($(this).html() == '下架'){
				teaState = 2;
			}
			ajax.updateTeaState(teaId,teaState).then((value)=>{
				let $appendItem = $item.clone();
					$appendItem.find('td:last').remove();
				if(teaState == 1){
					$item.find('.updateBtn').html('下架');
					$('#shelvesPro tbody').append($appendItem);
				}else if(teaState == 2){
					$item.find('.updateBtn').html('上架');
					let teaName = $item.find('td:eq(2)').html();
					$('#shelvesPro').find(`td:contains(${teaName})`).parent().remove();
				}
			})
		})
	});
	ajax.getShelvesProducts();
	// 更新茶

	// $('#checkModefied').click(function(){
	// 	let formData = new FormData($('#modefiedForm'))
	// 	$.ajax({
	// 		url:'/tea/updateTeaDetailed',
	// 		type:'post',
	// 		data:formData,
	// 		processData:false,
	// 		contentType:false,
	// 		cache:false,
	// 		success(data){
	// 			if(data.code == 1){
	// 				alert(data.msg)
	// 				$('#closeMod').trigger('click');
	// 			}
	// 		}
	// 	}).then(value=>{
	// 		// let teaId = $(this).siblings(':eq(0)').find('input').val()
	// 		let newName = $(this).siblings(':eq(1)').find('input').val(),
	// 			newIntro = $(this).siblings(':eq(2)').find('textarea').val(),
	// 			newNum = $(this).siblings(':eq(3)').find('input').val(),
	// 			newPrice = $(this).siblings(':eq(4)').find('input').val();
	// 		$modefiedTea.find('td:eq(2)').html(newName);
	// 		$modefiedTea.find('td:eq(3)').html(newIntro);
	// 		$modefiedTea.find('td:eq(4)').html(newNum);
	// 		$modefiedTea.find('td:eq(5)').html(newPrice);
	// 	})
	// 	return false;
	// })
    $('#checkModefied').click(function(){
        $('#modefiedForm').ajaxForm({
            url:'/tea/updateTeaDetailed',
            type:'post',
            success(data){
                // console.log(data)
                if(data.code == 1){
                    alert(data.msg);
                }else{
                    alert(data.msg)
                }
            }
        })

    })
	// 添加茶
	$('#checkAdd').click(function(e){
		// e.preventDefault();/
		$('#addForm').ajaxForm({
			url: '/tea/addTea',
			type:'post',
			success(data){
				console.log(data)
				if(data.code == 1){
					alert(data.msg);
				}else{
					alert(data.msg)
				}
			}
		})
	})	
	// 根据cookie显示页面
	if(CookieUtil.get('state') == 1){ //显示用户界面
		$('#managerView').remove();
	}else if(CookieUtil.get('state') == 2){ // 显示管理员页面
		$('#userView').remove();
	}
})