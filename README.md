#Vigencrypt

A very very simple implementation of the Vigenere encryption.

###Compilation

Assuming the current working directory is the Vigencrypt root:

```
javac src/codingotaku/vigencrypt/App.java -d .
```

###Encryption
Place the file to be encrypted in the Vigencrypt root and then run:
```
java codingotaku.vigencrypt.App -e "filename"
```

###Decryption
Place the file to be decrypted in the Vigencrypt root and then run:
```
java codingotaku.vigencrypt.App -d "filename"
```

####Note
The encrypte option is the default option. So, if the option is omitted, encryption is assumed.



