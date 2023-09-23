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

    return result;
}

void char_to_int(char text[], long long int converted[], int size)
{
    for (int i = 0; i < size; ++i)
    {
        converted[i] = text[i];
        printf("%d ", converted[i]);
    }

    printf("\n");
}

void cifrar(long long int converted[], int size, long long int n, long long int e)
{
    for (int i = 0; i < size; ++i)
    {
        converted[i] = modPow(converted[i],e,n);
        printf("%d ", converted[i]);
    }
}


int main()
{
    FILE *pk = fopen("paraBack/chaveCripto.txt", "r");
    FILE *pura = fopen("paraBack/mensagemPura.txt", "r");
    FILE *ctd = fopen("msgCript.txt", "w");

    char text[256];

    int i = 0;
    int size = 0;

    while((text[i] = fgetc(pura)) != EOF){
        i++;
        size++;
    }


    printf("texto : %s\n", text);
    printf("tamanho: %d\n", size);

    long long int n, e;
    fscanf(pk, "%lld %lld", &n, &e);

    printf("n = %lld e = %lld\n", n, e);

    long long int converted[size];

    char_to_int(text, converted, size);

    cifrar(converted, size, n, e);
    
    for (i = 0; i < size; ++i)
    {
        if (i == 0) fprintf(ctd,"%d", converted[i]);
        else fprintf(ctd," %d", converted[i]);
    }
    
    fclose(pk);
    fclose(ctd);

    return 0;
}
