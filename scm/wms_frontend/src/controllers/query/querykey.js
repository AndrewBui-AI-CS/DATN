export const queryKey = {
  category: {
    customer_list: "customer_list",
    product_list: "product_list",
    product_list_no_paging: "product_list_no_paging",
    product_cate_list: "product_cate_list",
    product_unit_list: "product_unit_list",
    dist_channel_list: "dist_channel_list",
    customer_type_list: "customer_type_list",
    contract_type_list: "contract_type_list",
  },
  purchase_order: {
    order_list: "purchase_order_list",
    order_item: "purchase_order_items",
    purchase_price: "purchase_price",
    export_pdf: "export_purchase_order_pdf",
  },
  sale_order: {
    order_list: "sale_order_list",
    order_item: "sale_order_items",
    sale_price: "sale_price",
  },
  facility: {
    facility_list: "facility_list",
    facility_list_no_paging: "facility_list_no_paging",
    facility_inventory: "facility_inventory",
    facility_customer: "facility_customer",
  },
  receipt_bill: {
    receipt_bill_list: "receipt_bill_list",
    bill_item_of_purchase_order: "bill_item_of_purchase_order",
    bill_item_of_purchase_order_paging: "bill_item_of_purchase_order_paging",
  },
  delivery_bill: {
    delivery_bill_list: "delivery_bill_list",
    bill_item_of_delivery_order: "bill_item_of_delivery_order",
    bill_item_of_bill: "bill_item_of_bill",
    splitted_bill_item: "splitted_bill_item",
    bill_item_by_seq: "bill_item_by_seq",
  },
  shipment: {
    shipment_list: "shipment_list",
    shipment_items: "shipment_items",
    trip_items: "trip_items",
  },
  delivery_trip: {
    trip_list: "trip_list",
    truck_list: "truck_list",
    drone_list: "drone_list",
    trip_assign_bill: "trip_assign_bill",
    trip_route_list: "trip_route_list",
  },
  user: {
    user_list: "user_list",
    user_list_all: "user_list_all",
    user_list_pagination: "user_list_pagination",
    user_list_all_by_role: "user_list_all_by_role",
  },
  dashboard: {
    new_facility_month: "new_facility_month",
    imported_product_cate: "imported_product_cate",
    quarter_purchase_order: "quarter_purchase_order",
    top_customer: "top_customer",
    trip_per_province: "trip_per_province",
    product_category_rate: "product_category_rate",
    new_customer_month: "new_customer_month",
    sale_annually: "sale_annually",
  },
};