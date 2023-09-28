#include <stdio.h>

long long modPow(long long base, long long exponent, long long modulo)
{
    long long result = 1;
    base %= modulo;

    while (exponent > 0)
    {
        if ( exponent % 2 == 1)
        {
            result = (result * base) % modulo;
        }

        base = (base * base) % modulo;
        exponent /= 2;
    }

    //printf("%llu ", result);

    return result;
}

long long modInverse(long long a, long long m)
{
    long long m0 = m;
    long long y = 0, x = 1;
 
    if (m == 1)
        return 0;
 
    while (a > 1) 
    {
        // q is quotient
        long long q = a / m;
        long long t = m;
 
        // m is remainder now, process same as
        // Euclid's algo
        m = a % m, a = t;
        t = y;
 
        // Update y and x
        y = x - q * y;
        x = t;
    }
 
    // Make x positive
    if (x < 0)
        x += m0;
 
    return x;
}

int main()
{
    FILE *pk = fopen("paraBack/chaveDescripto.txt", "r");
    FILE *ctd = fopen("paraBack/mensagemEncriptada.txt", "r");
    FILE *dctd = fopen("msgDcrpt.txt", "w");

// LE O 'D' E O 'N' DA CHAVE PRIVADA

    long long d, n, p, q, e, phi;
    fscanf(pk, "%lld %lld %lld", &p, &q, &e);
    n = p*q;
    phi = (p-1)*(q-1);
    d = modInverse(e,phi);
    printf("%lld %lld %lld %lld\n", n, phi, e, d);

    long long criptado, descriptado;

// LE A MENSAGEM ENCRIPTADA E DESENCRIPTA, SALVANDO NO ARQUIVO "descripted.txt"

    while (fscanf(ctd, "%lld", &criptado) != EOF)
    {
       // printf("%llu ", criptado);

        descriptado = modPow(criptado, d, n);

        printf("%lld ", descriptado);
        fprintf(dctd, "%c", (int)descriptado);
    }

// FECHA O ARQUIVO "descripted.txt"
    fclose(dctd);

// ABRE O ARQUIVO "descripted.txt" EM MODO DE LEITURA E IMPRIME O TEXTO DESCRIPTADO

    dctd = fopen("msgDcrpt.txt", "r");

    char c;

    do
    {
        c = fgetc(dctd);
        
        //printf("%c" , c);    
        
    } while ( c != EOF);

    printf("\n");


    fclose(pk);
    fclose(ctd);
    fclose(dctd);

    return 0;
}
