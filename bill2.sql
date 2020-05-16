--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: myfun(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.myfun() RETURNS trigger
    LANGUAGE plpgsql
    AS $$


declare


same boolean ;


loc boolean;


idd int ;


newrate numeric ;


begin


update cdr set customer_id = (select customer.id from customer where phone = new.origin ) where id = new.id;


update cdr set  rate_plan_id = (select customer.rate_plan_id from customer where phone = new.origin ) where id = new.id ;


update cdr set bill_id = (select customer.bill_id from customer where phone = new.origin ) where id = new.id ;

update cdr set billdate = (select customer.billing_date from customer where phone = new.origin ) where id = new.id ;


update cdr set paid = (select customer.paid from customer where phone = new.origin ) where id = new.id ;


if (new.service_id=1 or new.service_id=2 )then


if (left(New.dest,4)=left(New.origin,4)) then same := true; loc:=true;


elseif (left(New.dest,4 ) in ('2011','2015','2010')) then same := false ; loc :=true ;


else same:=false ; loc:=false ;


end if ;


idd:=(select svc_pkg.id from svc_pkg where svc_pkg.service_id= new.service_id and svc_pkg.rate_plan_id= (select customer.rate_plan_id from customer where phone = new.origin ) and  tarrif_id in (select tarrif_zone.id from tarrif_zone where same_net =same and local= loc  ) and time_id  in ( select time_pkg.id from  time_pkg  where new.time::time  >  time_pkg.start and new.time::time   < time_pkg.finish  ));


update cdr set svc_pkg_id =  idd where id = new.id;


else 


idd:=(select svc_pkg.id from svc_pkg where svc_pkg.service_id= new.service_id and svc_pkg.rate_plan_id= (select customer.rate_plan_id from customer where phone = new.origin ));


update cdr set svc_pkg_id = idd where id = new.id ;


end if ;


newrate := (select rate from svc_pkg where id = idd);


if(new.service_id=1)then


update cdr set   cost =(CEIL (new.volume::float/60) *  newrate) , rate = newrate , volumespent=(select CEIL (new.volume::float/60)) where id = new.id  ; 


elseif(new.service_id=3 and new.external_fees=0)then


update cdr set  cost =(CEIL (new.volume::float/(1024*1024)) *  newrate) , rate = newrate , volumespent=(select CEIL (new.volume::float/(1024*1024))) where id = new.id  ;


elseif(new.service_id=3 )then


update cdr set  cost =new.external_fees  , rate = newrate , volumespent=(select CEIL (new.volume::float/(1024*1024))) where id = new.id  ;


elseif(new.service_id=2)then


update cdr set cost = (new.volume  * newrate) , rate = newrate , volumespent=new.volume where id = new.id  ;


else 


newrate := (select rate from svc_pkg where service_id = new.service_id);


update cdr set cost = (new.volume  * newrate ), rate = newrate , volumespent=new.volume  where id = new.id  ;


end if ;


return new ;


end;


$$;


ALTER FUNCTION public.myfun() OWNER TO postgres;

--
-- Name: mytrigger(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.mytrigger() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF OLD.billing_date < NEW.billing_date THEN
        update customer set bill_id =bill_id+1 where id = new.id ;
    END IF;
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.mytrigger() OWNER TO postgres;

--
-- Name: sumsubtotal(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.sumsubtotal(idd text, bill integer) RETURNS numeric
    LANGUAGE plpgsql
    AS $$ 
declare 
subtotal numeric ;
begin 
subtotal:=(select COALESCE ((select sum(cost) from (select cdr.service_id, cdr.svc_pkg_id,cdr.rate ,sum(volumespent) as "volume spent",(select sum(volumespent)-free_units from svc_pkg where svc_pkg.id =cdr.svc_pkg_id) as "after rate plan" ,(select( sum (volumespent)-free_units)* rate from  svc_pkg where svc_pkg.id =cdr.svc_pkg_id ) as  cost  , (select free_units from svc_pkg where svc_pkg.id = cdr.svc_pkg_id) as "free units" ,billdate ,cdr.rate_plan_id , cdr.paid , bill_id from cdr  where right(origin,11) =idd and bill_id= bill group by svc_pkg_id ,cdr.service_id ,cdr.rate , billdate  , cdr.rate_plan_id, cdr.paid ,cdr.bill_id ) as invoice where cost>0) ,0))+(select COALESCE(sum(cost),0) from cust_svc_addons where cust_id=(select id from customer where right(phone,11)=idd) and bill_id=bill)+(select monthly_fees from rate_plan,customer where rate_plan.id=customer.id and customer.id=(select id from customer where right (phone,11) =idd));
return subtotal ;
end ;
$$;


ALTER FUNCTION public.sumsubtotal(idd text, bill integer) OWNER TO postgres;

--
-- Name: sumtax(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.sumtax(idd text, bill integer) RETURNS double precision
    LANGUAGE plpgsql
    AS $$
declare 
tax float  ;
begin
tax:=(select 0.1 *(select sumsubtotal(idd,bill)));
return tax ;
end ;
$$;


ALTER FUNCTION public.sumtax(idd text, bill integer) OWNER TO postgres;

--
-- Name: sumtotal(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.sumtotal(idd text, bill integer) RETURNS double precision
    LANGUAGE plpgsql
    AS $$




declare 




total float ;




begin




total=(select ((select sumtax(idd,bill))+ (select sumsubtotal(idd,bill))));
return total ;




end ;




$$;


ALTER FUNCTION public.sumtotal(idd text, bill integer) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: bills; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bills (
    id integer,
    customer_id integer,
    totalcost numeric,
    billdate date,
    paid boolean,
    service_id integer,
    rate_plan_id integer
);


ALTER TABLE public.bills OWNER TO postgres;

--
-- Name: cdr; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cdr (
    id integer NOT NULL,
    origin text,
    dest text,
    service_id integer,
    volume numeric,
    date date,
    "time" text,
    external_fees numeric,
    customer_id integer,
    rate numeric,
    cost numeric,
    time_zone_id integer,
    tarrif_zone_id integer,
    rate_plan_id integer,
    start time without time zone,
    finish time without time zone,
    svc_pkg_id integer,
    volumespent numeric,
    paid boolean,
    bill_id integer,
    billdate timestamp without time zone
);


ALTER TABLE public.cdr OWNER TO postgres;

--
-- Name: cdr_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cdr_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cdr_id_seq OWNER TO postgres;

--
-- Name: cdr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cdr_id_seq OWNED BY public.cdr.id;


--
-- Name: cust_svc_addons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cust_svc_addons (
    cust_id integer,
    service_id integer,
    cost numeric,
    paid boolean,
    billdate timestamp without time zone,
    bill_id integer
);


ALTER TABLE public.cust_svc_addons OWNER TO postgres;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    id integer NOT NULL,
    rate_plan_id integer,
    name text,
    phone text,
    email text,
    address text,
    bill_id integer DEFAULT 1,
    paid boolean DEFAULT false,
    nid text,
    billing_date timestamp without time zone
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- Name: customer_id; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer_id (
    id integer
);


ALTER TABLE public.customer_id OWNER TO postgres;

--
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_id_seq OWNER TO postgres;

--
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- Name: rate_plan; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rate_plan (
    id integer NOT NULL,
    name text,
    monthly_fees numeric
);


ALTER TABLE public.rate_plan OWNER TO postgres;

--
-- Name: rate_plan_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rate_plan_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rate_plan_id_seq OWNER TO postgres;

--
-- Name: rate_plan_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rate_plan_id_seq OWNED BY public.rate_plan.id;


--
-- Name: service; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.service (
    id integer NOT NULL,
    name text,
    rating boolean,
    type text
);


ALTER TABLE public.service OWNER TO postgres;

--
-- Name: service_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.service_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.service_id_seq OWNER TO postgres;

--
-- Name: service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.service_id_seq OWNED BY public.service.id;


--
-- Name: svc_pkg; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.svc_pkg (
    id integer NOT NULL,
    rate_plan_id integer,
    service_id integer,
    time_id integer,
    tarrif_id integer,
    free_units integer,
    rate numeric
);


ALTER TABLE public.svc_pkg OWNER TO postgres;

--
-- Name: svc_pkg_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.svc_pkg_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.svc_pkg_id_seq OWNER TO postgres;

--
-- Name: svc_pkg_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.svc_pkg_id_seq OWNED BY public.svc_pkg.id;


--
-- Name: tarrif_zone; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tarrif_zone (
    id integer NOT NULL,
    name text,
    same_net boolean,
    local boolean
);


ALTER TABLE public.tarrif_zone OWNER TO postgres;

--
-- Name: tarrif_zone_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tarrif_zone_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tarrif_zone_id_seq OWNER TO postgres;

--
-- Name: tarrif_zone_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tarrif_zone_id_seq OWNED BY public.tarrif_zone.id;


--
-- Name: time_pkg; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.time_pkg (
    id integer NOT NULL,
    name text,
    start time without time zone,
    finish time without time zone,
    day text
);


ALTER TABLE public.time_pkg OWNER TO postgres;

--
-- Name: time_pkg_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.time_pkg_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.time_pkg_id_seq OWNER TO postgres;

--
-- Name: time_pkg_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.time_pkg_id_seq OWNED BY public.time_pkg.id;


--
-- Name: cdr id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cdr ALTER COLUMN id SET DEFAULT nextval('public.cdr_id_seq'::regclass);


--
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- Name: rate_plan id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rate_plan ALTER COLUMN id SET DEFAULT nextval('public.rate_plan_id_seq'::regclass);


--
-- Name: service id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.service ALTER COLUMN id SET DEFAULT nextval('public.service_id_seq'::regclass);


--
-- Name: svc_pkg id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.svc_pkg ALTER COLUMN id SET DEFAULT nextval('public.svc_pkg_id_seq'::regclass);


--
-- Name: tarrif_zone id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tarrif_zone ALTER COLUMN id SET DEFAULT nextval('public.tarrif_zone_id_seq'::regclass);


--
-- Name: time_pkg id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.time_pkg ALTER COLUMN id SET DEFAULT nextval('public.time_pkg_id_seq'::regclass);


--
-- Data for Name: bills; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bills (id, customer_id, totalcost, billdate, paid, service_id, rate_plan_id) FROM stdin;
\.


--
-- Data for Name: cdr; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cdr (id, origin, dest, service_id, volume, date, "time", external_fees, customer_id, rate, cost, time_zone_id, tarrif_zone_id, rate_plan_id, start, finish, svc_pkg_id, volumespent, paid, bill_id, billdate) FROM stdin;
258	2011221234567	201201234567	1	1	2020-07-01	10:23:22	0	1	0.5	0.5	\N	\N	1	\N	\N	3	1	f	3	2020-06-01 12:00:00
243	2011221234567	201001234567	3	32	2019-03-01	2020-05-15 10:20:17.373909+02	20	1	0.2	20	\N	\N	1	\N	\N	6	1	t	1	2020-05-07 00:00:00
267	2011221234567	201201234567	3	200000	2019-03-01	10:23:22	0	1	0.2	0.2	\N	\N	1	\N	\N	6	1	f	3	2020-06-01 12:00:00
237	2011221234567	201001234567	1	100	2019-03-01	2020-05-15 10:07:53.028335+02	0	1	0.2	0.4	\N	\N	1	\N	\N	2	2	t	1	2020-05-07 00:00:00
244	2011221234567	201201234567	1	100	2019-03-01	10:23:22	0	1	0.5	1	\N	\N	1	\N	\N	3	2	f	1	2020-05-07 00:00:00
262	2011221234567	201201234567	1	2000	2019-03-01	10:23:22	0	1	0.5	17	\N	\N	1	\N	\N	3	34	f	3	2020-06-01 12:00:00
238	2011221234567	201001234567	2	100	2019-03-01	2020-05-15 10:08:21.79198+02	0	1	\N	\N	\N	\N	1	\N	\N	\N	100	t	1	2020-05-07 00:00:00
268	2011221234567	201201234567	3	2000000	2019-03-01	10:23:22	0	1	0.2	0.4	\N	\N	1	\N	\N	6	2	f	3	2020-06-01 12:00:00
245	201015409319	201201234567	1	100	2019-03-01	10:23:22	0	3	\N	\N	\N	\N	2	\N	\N	\N	2	f	1	2020-05-07 00:00:00
239	2011221234567	201001234567	3	100	2019-03-01	2020-05-15 10:09:23.16749+02	0	1	0.2	0.2	\N	\N	1	\N	\N	6	1	t	1	2020-05-07 00:00:00
263	2011221234567	201201234567	3	20000	2019-03-01	10:23:22	0	1	0.2	0.2	\N	\N	1	\N	\N	6	1	f	3	2020-06-01 12:00:00
246	201015409319	201201234567	1	100	2019-03-01	10:23:22	0	3	\N	\N	\N	\N	2	\N	\N	\N	2	f	1	2020-05-07 00:00:00
240	2011221234567	201001234567	3	12223232	2019-03-01	2020-05-15 10:10:10.418193+02	0	1	0.2	2.4	\N	\N	1	\N	\N	6	12	t	1	2020-05-07 00:00:00
264	2011221234567	201201234567	3	20000	2019-03-01	10:23:22	0	1	0.2	0.2	\N	\N	1	\N	\N	6	1	f	3	2020-06-01 12:00:00
241	2011221234567	201001234567	3	32	2019-03-01	2020-05-15 10:11:13.193783+02	32	1	0.2	32	\N	\N	1	\N	\N	6	1	t	1	2020-05-07 00:00:00
247	2011221234567	201201234567	1	100	2019-03-01	10:23:22	0	1	0.5	1	\N	\N	1	\N	\N	3	2	f	1	2020-05-07 00:00:00
242	2011221234567	201001234567	3	0	2019-03-01	2020-05-15 10:18:29.982766+02	32	1	0.2	32	\N	\N	1	\N	\N	6	0	t	1	2020-05-07 00:00:00
248	2011221234567	201201234567	1	100	2019-03-01	10:23:22	0	1	0.5	1	\N	\N	1	\N	\N	3	2	f	1	2020-05-15 11:52:00
265	2011221234567	201201234567	3	200000	2019-03-01	10:23:22	0	1	0.2	0.2	\N	\N	1	\N	\N	6	1	f	3	2020-06-01 12:00:00
249	2011221234567	201201234567	1	100	2019-03-01	10:23:22	0	1	0.5	1	\N	\N	1	\N	\N	3	2	f	2	2020-05-15 11:52:00
266	2011221234567	201201234567	3	200000	2019-03-01	10:23:22	0	1	0.2	0.2	\N	\N	1	\N	\N	6	1	f	3	2020-06-01 12:00:00
250	2011221234567	201201234567	1	100	2019-03-01	10:23:22	0	1	0.5	1	\N	\N	1	\N	\N	3	2	f	2	2020-05-15 11:52:00
251	2011221234567	201201234567	1	1	2020-07-01	10:23:22	0	1	0.5	0.5	\N	\N	1	\N	\N	3	1	f	2	2020-05-15 11:52:00
259	2011221234567	201201234567	1	100	2019-03-01	10:23:22	0	1	0.5	1	\N	\N	1	\N	\N	3	2	f	3	2020-06-01 12:00:00
252	2011221234567	201201234567	1	1	2020-05-20	10:23:22	0	1	0.5	0.5	\N	\N	1	\N	\N	3	1	f	3	2020-06-01 12:00:00
260	2011221234567	201201234567	1	100	2019-03-01	10:23:22	0	1	0.5	1	\N	\N	1	\N	\N	3	2	f	3	2020-06-01 12:00:00
253	2011221234567	201201234567	1	1	2020-07-01	10:23:22	0	1	0.5	0.5	\N	\N	1	\N	\N	3	1	f	3	2020-06-01 12:00:00
261	2011221234567	201201234567	1	100	2019-03-01	10:23:22	0	1	0.5	1	\N	\N	1	\N	\N	3	2	f	3	2020-06-01 12:00:00
254	2011221234567	201201234567	1	3	2020-07-01	10:23:22	0	1	0.5	0.5	\N	\N	1	\N	\N	3	1	f	3	2020-06-01 12:00:00
255	2011221234567	201201234567	1	1	2020-07-01	10:23:22	0	1	0.5	0.5	\N	\N	1	\N	\N	3	1	f	3	2020-06-01 12:00:00
269	2011221234567	201201234567	3	20000000	2019-03-01	10:23:22	0	1	0.2	4	\N	\N	1	\N	\N	6	20	f	3	2020-06-01 12:00:00
256	2011221234567	201201234567	1	1	2020-07-01	10:23:22	0	1	0.5	0.5	\N	\N	1	\N	\N	3	1	f	3	2020-06-01 12:00:00
257	2011221234567	201201234567	3	1	2020-07-01	10:23:22	0	1	0.2	0.2	\N	\N	1	\N	\N	6	1	f	3	2020-06-01 12:00:00
270	201002062634	201201234567	1	100	2019-03-01	10:23:22	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	2	\N	\N	\N
271	201002062634	201201234567	1	700	2019-03-01	10:23:22	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	12	\N	\N	\N
272	201002062634	201201234567	1	700	2019-03-01	10:23:22	0	4	\N	\N	\N	\N	11	\N	\N	\N	12	f	1	2020-05-11 00:00:00
273	201002062634	201001234567	1	700	2019-03-01	10:23:22	0	4	0.3	3.6	\N	\N	11	\N	\N	10	12	f	1	2020-05-11 00:00:00
274	201002062634	201001234567	1	700	2019-03-01	10:23:22	0	4	0.3	3.6	\N	\N	11	\N	\N	10	12	f	1	2020-05-11 00:00:00
282	201002062634	201201234567	3	1000000000	2019-03-01	10:23:22	0	4	0.1	95.4	\N	\N	11	\N	\N	12	954	f	1	2020-05-11 00:00:00
275	201201234567	201201234567	1	100	2019-03-01	10:23:22	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	2	\N	\N	\N
283	201002062635	201201234567	3	10000000	2019-03-01	10:23:22	0	5	0.2	2	\N	\N	1	\N	\N	6	10	f	1	2020-05-04 00:00:00
276	2011221234567	201201234567	1	100	2019-03-01	10:23:22	0	1	0.5	1	\N	\N	1	\N	\N	3	2	f	4	2020-06-02 12:00:00
284	00201221234567	00201001234567	1	100	2019-03-01	10:03:20	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	2	\N	\N	\N
277	2011221234567	201201234567	1	100	2019-03-01	10:23:22	0	1	0.5	1	\N	\N	1	\N	\N	3	2	f	4	2020-06-02 12:00:00
278	2011221234567	201201234567	1	10000	2019-03-01	10:23:22	0	1	0.5	83.5	\N	\N	1	\N	\N	3	167	f	4	2020-06-02 12:00:00
285	00201221239999	00201001234567	1	100	2019-03-01	10:03:20	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	2	\N	\N	\N
279	2011221234567	201201234567	1	10000	2019-03-01	10:23:22	0	1	0.5	83.5	\N	\N	1	\N	\N	3	167	f	4	2020-06-02 12:00:00
286	00201221239999	00201001234567	1	100	2019-03-01	10:03:20	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	2	\N	\N	\N
280	2011221234567	201201234567	3	1000000	2019-03-01	10:23:22	0	1	0.2	0.2	\N	\N	1	\N	\N	6	1	f	4	2020-06-02 12:00:00
281	2011221234567	201201234567	3	100000000000	2019-03-01	10:23:22	0	1	0.2	19073.6	\N	\N	1	\N	\N	6	95368	f	4	2020-06-02 12:00:00
\.


--
-- Data for Name: cust_svc_addons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cust_svc_addons (cust_id, service_id, cost, paid, billdate, bill_id) FROM stdin;
3	5	19	f	2020-05-15 10:23:06.431578	3
1	5	19	f	2020-05-15 10:23:49.711054	3
3	2	19	f	2020-05-15 11:26:53.505061	3
3	1	19	f	2020-05-15 11:27:08.998947	3
4	4	100	f	2020-05-15 11:27:08.998947	1
4	5	200	f	2020-05-15 11:27:08.998947	1
5	4	100	f	\N	1
5	5	200	f	\N	1
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (id, rate_plan_id, name, phone, email, address, bill_id, paid, nid, billing_date) FROM stdin;
2	1	mostafa	1015409319	mostafalove5070@yahoo.com	mmmm	4	f	\N	2020-06-02 12:00:00
1	1	mostafa	2011221234567	mostafalove5070@yahoo.com	mmmm	4	f	\N	2020-06-02 12:00:00
3	2	mostafa	201015409319	mostafalove5070@yahoo.com	mmmm	5	f	\N	2020-06-03 12:00:00
4	12	Mohamed Ibrahim	201002062634	mohamedco215@gmail.com	Damietta	1	f	29608111100656	2020-05-11 00:00:00
\.


--
-- Data for Name: customer_id; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer_id (id) FROM stdin;
\.


--
-- Data for Name: rate_plan; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rate_plan (id, name, monthly_fees) FROM stdin;
1	star	100
2	st	200
11	r100	100
12	r200	2416
\.


--
-- Data for Name: service; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.service (id, name, rating, type) FROM stdin;
1	voice	t	network
2	sms	t	network
3	data	t	network
4	Rs	t	Recurring Services
5	OTE	t	One time fee
\.


--
-- Data for Name: svc_pkg; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.svc_pkg (id, rate_plan_id, service_id, time_id, tarrif_id, free_units, rate) FROM stdin;
1	1	1	2	1	5	0.2
2	1	1	2	2	5	0.2
3	1	1	2	3	10	0.5
4	1	2	2	1	5	0.2
6	1	3	2	1	5	0.2
7	2	1	3	1	5	0.2
8	2	1	1	1	5	0.2
9	2	3	2	1	5	0.2
10	11	1	2	1	20	0.3
11	11	2	2	2	15	0.2
12	11	3	2	3	40	0.1
13	12	1	2	1	20	0.3
14	12	2	1	2	15	0.2
15	12	3	2	1	40	0.1
\.


--
-- Data for Name: tarrif_zone; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tarrif_zone (id, name, same_net, local) FROM stdin;
1	mynet	t	t
2	othnet	f	t
3	roam	f	f
\.


--
-- Data for Name: time_pkg; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.time_pkg (id, name, start, finish, day) FROM stdin;
2	allday	00:00:00	23:59:59	All
1	firsthalfday	00:00:00	11:59:59	All
3	secondhalfday	12:00:00	23:59:59	All
\.


--
-- Name: cdr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cdr_id_seq', 286, true);


--
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_id_seq', 5, true);


--
-- Name: rate_plan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rate_plan_id_seq', 12, true);


--
-- Name: service_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.service_id_seq', 6, true);


--
-- Name: svc_pkg_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.svc_pkg_id_seq', 15, true);


--
-- Name: tarrif_zone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tarrif_zone_id_seq', 4, true);


--
-- Name: time_pkg_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.time_pkg_id_seq', 1, true);


--
-- Name: cdr cdr_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cdr
    ADD CONSTRAINT cdr_pkey PRIMARY KEY (id);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- Name: rate_plan rate_plan_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rate_plan
    ADD CONSTRAINT rate_plan_pkey PRIMARY KEY (id);


--
-- Name: service service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);


--
-- Name: svc_pkg svc_pkg_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.svc_pkg
    ADD CONSTRAINT svc_pkg_pkey PRIMARY KEY (id);


--
-- Name: tarrif_zone tarrif_zone_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tarrif_zone
    ADD CONSTRAINT tarrif_zone_pkey PRIMARY KEY (id);


--
-- Name: time_pkg time_pkg_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.time_pkg
    ADD CONSTRAINT time_pkg_pkey PRIMARY KEY (id);


--
-- Name: customer billtrigger; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER billtrigger AFTER UPDATE OF billing_date ON public.customer FOR EACH ROW EXECUTE FUNCTION public.mytrigger();


--
-- Name: cdr mytrigger; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER mytrigger AFTER INSERT ON public.cdr FOR EACH ROW EXECUTE FUNCTION public.myfun();


--
-- PostgreSQL database dump complete
--

