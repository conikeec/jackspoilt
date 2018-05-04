**What are we proving**
1. Jackson Databind Deserialization vulnerability (https://docs.google.com/document/d/1MmF27tVvUbROMFQQRmjw-_syDnGxF1nNWbls0xY0vg4/edit?usp=sharing)

**Context of application**
1. Microservice based on Spark Framework (http://sparkjava.com/)
1. Depends on 
   1. com.fasterxml.jackson.core:jackson-databind:2.8.8
   1. xalan:xalan:2.7.2
1. Unsafe deserializaiton (Polymorphic Typing)
1. Untrusted input acceptance

**Directory Structure**

1. _src/..._ : Source code to depict Deserializaiton Exploit (uses http://sparkjava.com/)
2. _exploit_ : Source code to craft a gadget/exploit for DeSerializaiton Vulnerability
3. _attackscripts_ : Misc shell scripts to induce normal and malicious requests upon applicaiton service

**How can I use this?**

Spin up a shell prompt (start vulnerable web instance)

1. _git clone https://github.com/conikeec/jackspoilt.git_
2. _cd jackspoilt_
3. _mvn package_
4. Start application server : _java -jar target/jackspoilt-1.0-SNAPSHOT.jar_ 

Spin up a shell prompt (To create gadget or exploit)

1. _cd jackspoilt_
2. mvn exec:java -D"exec.mainClass"="EncodeExploit"
3. The command above creates _attack.json_ in the _attackscripts_ directory
4. _cd attackscripts_
5. _./add.sh_
6. _./list.sh_
7. _./exploit.sh_ - This command will ineject the malicious payload, trigger gadget chain (Edit _exploit/Exploit.java_ to add your exploit command of choice)

