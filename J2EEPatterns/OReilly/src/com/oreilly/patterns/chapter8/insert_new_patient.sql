PROCEDURE INSERT_NEW_PATIENT 
(
  fname_in  in  patient.fname%type,
  lname_in  in  patient.lname%type,
  pat_no    out patient.pat_no%type
) AS
new_pat_no  patient.pat_no%type;
BEGIN
  select seq_pk.nextval into new_pat_no from dual;

  insert into patient (pat_no, fname, lname) 
    values (new_pat_no, fname_in, lname_in);
    
  pat_no := new_pat_no;
END;
