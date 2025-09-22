const products = {
  1: [
    
    {
      img: "asses/img/TayDuKy.jpg",
      title: "Tây Du Ký",
      price: "62,000₫",
      link: "#"
    },
    {
      img: "asses/img/AnhHungXaDieu.jpg",
      title: "Anh Hùng Xạ Điêu",
      price: "75,000₫",
      link: "#"
    },
    {
      img: "asses/img/YThienDoLongKy.jpg",
      title: "Ỷ Thiên Đồ Long Ký",
      price: "68,000₫",
      link: "#"
    }
  ],
  2: [
    
     {
      img: "asses/img/YThienDoLongKy.jpg",
      title: "Ỷ Thiên Đồ Long Ký",
      price: "68,000₫",
      link: "#"
    },
    {
      img: "asses/img/TamQuocDienNghia.png",
      title: "Tam Quốc Diễn Nghĩa",
      price: "80,000₫",
      link: "#"
    },
    {
      img: "asses/img/PhongThanBang.png",
      title: "Phong Thần Bảng",
      price: "65,000₫",
      link: "#"
    }
  ],
  3: [
   
    {
      img: "asses/img/TruyenKieu.png",
      title: "Truyện Kiều",
      price: "55,000₫",
      link: "#"
    },
    {
      img: "asses/img/LucVanTien.png",
      title: "Lục Vân Tiên",
      price: "58,000₫",
      link: "#"
    },
    {
      img: "asses/img/ChiPheo.png",
      title: "Chí Phèo",
      price: "52,000₫",
      link: "#"
    }
  ]
};


function loadProduct(page){
    const listItem = document.getElementById("list_card");
    listItem.innerHTML = products[page]
    .map((p) => `
         <div class="list_item">
          <img src="${p.img}" alt="${p.title}" />
          <p class="item_title_img">${p.title}</p>
          <div class="item_underline"></div>
          <div class="item_money">${p.price}</div>
          <div class="item_details"><a href="${p.link}">Xem chi tiết</a></div>
        </div>    
    `).join("");

}


document.querySelectorAll(".pagination a").forEach(
    (link) => link.addEventListener("click",
        e =>{
            e.preventDefault();
            const page = e.target.dataset.page;
            loadProduct(page);
        } 
    )
);

loadProduct(1);