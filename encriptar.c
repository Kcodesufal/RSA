#include <stdio.h>
#include <stdlib.h>
#include <string.h>

long long modPow(long long base, long long exponent, long long modulo)
{
    long long result = 1;
    base %= modulo;

    while (exponent > 0)
    {
        if (exponent % 2 == 1)
        {
            result = (result * base) % modulo;
        }

        base = (base * base) % modulo;
        exponent /= 2;
    }

    return result;
}

void cifrar(long long int converted[], int size, long long int n, long long int e)
{
    for (int i = 0; i < size; ++i)
    {
        converted[i] = modPow(converted[i], e, n);
        printf("%lld ", converted[i]);
    }
    printf("\n");
}

int main()
{
    FILE *pk = fopen("paraBack/chaveCripto.txt", "r");
    FILE *pura = fopen("paraBack/mensagemPura.txt", "r");
    FILE *ctd = fopen("msgCript.txt", "w");

    int size = 0;
    int i = 0;

    if (pk == NULL)
    {
        printf("Error: could not open file.\n");
        exit(1);
    }

    // read n and e from file
    long long int n, e;
    fscanf(pk, "%lld %lld", &n, &e);

    // read text from file
    char text[2500];
    
    while ((text[i] = fgetc(pura)) != EOF)
    {
        i++;
        size++;
    }

    // convert text to long long int array
    long long int converted[size];
    for (i = 0; i < size; ++i)
    {
        converted[i] = text[i];
        printf("%lld ", converted[i]);
    }
    printf("\n");

    // encrypt text
    cifrar(converted, size, n, e);

    for (i = 0; i < size; ++i)
    {
        if (i == 0)
            fprintf(ctd, "%lld", converted[i]);
        else
            fprintf(ctd, " %lld", converted[i]);
    }

    fclose(pk);
    fclose(pura);
    fclose(ctd);

    return 0;
}
