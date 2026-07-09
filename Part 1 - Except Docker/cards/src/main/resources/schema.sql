-- please note that auto_increment only work with int,bigint etc not numeric
create table if not exists cards(card_id int auto_increment Primary Key,
                                 card_number varchar(100) not null,email varchar(100) not null,
    card_type varchar(20) not null,
    total_limit int not null,
    amount_used int not null,
    available_amount int not null,
    created_at date not null,
    created_by varchar(100) not null,
    updated_at date default null,
    updated_by varchar(100) default null);