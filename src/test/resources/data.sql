insert into public.users (is_reported, email, password, status, user_type)
values  (true, 'n.maric1912@gmail.com', '$2a$10$XX4IE3b7w24B.61scbacROAzo5gbQlsDqE81uh0HxONhEugNRzyW.', 'ACTIVE', 'HOST'),
        (true, 'nmaric1912@gmail.com', '$2a$10$XX4IE3b7w24B.61scbacROAzo5gbQlsDqE81uh0HxONhEugNRzyW.', 'ACTIVE', 'GUEST');

insert into public.notificaton_type_status (is_turned,user_id, type)
values  (true, 2, 'RESERVATION_REQUEST_RESPOND'),
        (true,1, 'RESERVATION_REQUEST'),
        (true,1, 'CREATED_RESERVATION'),
        (true,1, 'CANCELLED_RESERVATION'),
        (true, 1, 'NEW_REVIEW');


insert into public.addresses (latitude, longitude, postal_code, city, state, street)
values  (0, 0, 17500, 'V', 'Srbija', 'j'),
        (0, 0, 17500, 'Vranje', 'Srbija', 'Vase Smajevica 9'),
        (0, 0, 17500, 'Vranje', 'Srbija', 'Vase Smajevica 9');
insert into public.hosts (is_notification_enabled, address_id, user_id, last_name, name, phone_number)
values  (true, 2, 1, 'Maric', 'Marko', '0654506063');

insert into public.guests (is_notification_enabled, number_of_cancellation, address_id,user_id, last_name, name, phone_number)
values  (true, 0, 1, 2, 'Markovic', 'Nikola', '0654506063');

insert into public.accommodations (is_price_for_entire_acc, max_guests, min_guests, address_id, host_id, description, accommodation_approval_status, cancellation_policy, images, reservation_method, title, type)
values  (false, 5, 1, 3, 1,'asgasdfgasdfgsdfgsdfgsdfgsdfgsdfgafsdf', 'APPROVED', 'NON_REFUNDABLE', null, 'AUTOMATIC', 'Ceca Mobilne', 'HOTEL');

insert into public.price_lists (date, price, status)
values  ('2024-01-19', 200, 'AVAILABLE'),
        ('2024-01-20', 200, 'AVAILABLE'),
        ('2024-01-21', 200, 'AVAILABLE'),
        ('2024-01-22', 200, 'AVAILABLE'),
        ('2024-01-23', 200, 'AVAILABLE'),
        ('2024-01-24', 200, 'AVAILABLE'),
        ('2024-01-25', 200, 'AVAILABLE'),
        ('2024-01-26', 200, 'AVAILABLE'),
        ('2024-01-27', 200, 'AVAILABLE');

insert into public.accommodations_prices (accommodation_id, prices_id)
values  (1, 1),
        (1, 2),
        (1, 3),
        (1, 4),
        (1, 5),
        (1, 6),
        (1, 7),
        (1, 8),
        (1, 9);

insert into public.reservations (end_date, is_guest_reported, is_host_reported, number_of_guests, price, start_date, status, accommodation_id, guest_id)
values  ('2024-01-23', false, false, 2, 800, '2024-01-22', 'PENDING', 1, 1);