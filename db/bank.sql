PGDMP                       }            bank    14.13    16.4     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17265    bank    DATABASE     w   CREATE DATABASE bank WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_India.1252';
    DROP DATABASE bank;
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            �           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    4            �            1259    17307    transactions    TABLE     �   CREATE TABLE public.transactions (
    id integer NOT NULL,
    account_no integer NOT NULL,
    type character varying NOT NULL,
    amount integer NOT NULL,
    date time without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);
     DROP TABLE public.transactions;
       public         heap    postgres    false    4            �            1259    17306    transactions_id_seq    SEQUENCE     �   CREATE SEQUENCE public.transactions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.transactions_id_seq;
       public          postgres    false    211    4            �           0    0    transactions_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.transactions_id_seq OWNED BY public.transactions.id;
          public          postgres    false    210            �            1259    17277    users    TABLE     �   CREATE TABLE public.users (
    account_no integer NOT NULL,
    name character varying NOT NULL,
    balance integer NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false    4            `           2604    17310    transactions id    DEFAULT     r   ALTER TABLE ONLY public.transactions ALTER COLUMN id SET DEFAULT nextval('public.transactions_id_seq'::regclass);
 >   ALTER TABLE public.transactions ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    211    210    211            �          0    17307    transactions 
   TABLE DATA           J   COPY public.transactions (id, account_no, type, amount, date) FROM stdin;
    public          postgres    false    211   �       �          0    17277    users 
   TABLE DATA           :   COPY public.users (account_no, name, balance) FROM stdin;
    public          postgres    false    209   &       �           0    0    transactions_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.transactions_id_seq', 3, true);
          public          postgres    false    210            e           2606    17315    transactions transactions_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.transactions DROP CONSTRAINT transactions_pkey;
       public            postgres    false    211            c           2606    17283    users users_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (account_no);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    209            f           2606    17316 )   transactions transactions_account_no_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_account_no_fkey FOREIGN KEY (account_no) REFERENCES public.users(account_no);
 S   ALTER TABLE ONLY public.transactions DROP CONSTRAINT transactions_account_no_fkey;
       public          postgres    false    3171    211    209            �   l   x�M�M
�0�u�0a���ͬ=�ছ���z}����� j^NC�t�>�e^�3s5 ٪��Q^��q�mk��i	p���@zԗ�^�}��x�(�-�4f"�$)�      �   9   x�3426153��4�,.O,���Qp��,J�43200�2�I�¤�8-�b���� �/     