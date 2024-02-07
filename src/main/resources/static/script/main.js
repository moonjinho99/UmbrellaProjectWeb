
function menu_click(ths,id){
     let buttonValue = $(ths).text().trim();
     console.log(buttonValue, id);

     if(id==='admin'){
         $.ajax({
                    type: 'GET',
                    url: '/admin-main-menu',
                    data: {buttonValue: buttonValue},
                    success: function (data) {
                        $('#ajaxSection').html(data);
                    },
                    error: function (xhr, status, error) {
                        console.error("에러 발생:", error);
                    }
                });
     }else if(id!=='admin'){
        $.ajax({
                    type: 'GET',
                    url: '/center-main-menu',
                    data: {buttonValue: buttonValue},
                    success: function (data) {
                        $('#ajaxSection').html(data);
                    },
                    error: function (xhr, status, error) {
                        console.error("에러 발생:", error);
                    }
                });
     }

}

//admin js 시작
//function reset(id){
//
//   console.log(page);
//   console.log(id);
//   if(id==='admin'){
//      if(page==='admin/admin_center_enroll'){
//            window.reload();
//      }else if(page==='admin/admin_center_manage'){
//            window.reload();
//      }else if(page==='admin/admin_center_detail'){
//
//      }else if(page==='admin/admin_center_modify'){
//
//      }
//   }else if(id!=='admin'){
//
//   }
//}
//admin js 끝

