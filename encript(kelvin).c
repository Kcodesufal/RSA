#include <stdio.h>

long long int expo(int number, long long int expoent)
{
    if (expoent == 0)
    {
        return 1;
    }
    else
    {
        return number * expo(number, expoent-1);
    }
}

int whatsize(char text[])
{
    int count = 0;
    for (int i = 0; text[i] != '\n' ; ++i)
    {
        count++;
    }

    return count;
}

void char_to_int(char text[], int converted[], int size)
{
    for (int i = 0; i < size; ++i)
    {
        converted[i] = text[i];
        printf("%d ", converted[i]);
    }

    printf("\n");
}

void cifrar(int converted[], int size, long long int n, long long int e)
{
    long long int aux;
    for (int i = 0; i < size; ++i)
    {
        aux = expo(converted[i], e) % n;

        while (aux <= 0) aux += n;

        while(aux > n) aux -= n;

        converted[i] = aux;
        printf("%d ", converted[i]);
    }
}


int main()
{
    FILE *pk = fopen("public_key.txt", "r");
    FILE *ctd = fopen("encripted.txt", "w");

    char text[256];

    fgets(text, 255, stdin);

    int size = whatsize(text);

    printf("texto : %s", text);
    printf("tamanho: %d\n", size);

    long long int n, e;
    fscanf(pk, "%Ld%Ld", &n, &e);

    printf("n = %Ld e = %Ld\n", n, e);

    int converted[size];

    char_to_int(text, converted, size);

    cifrar(converted, size, n, e);
    
    for (int i = 0; i < size; ++i)
    {
        if (i == 0) fprintf(ctd,"%d", converted[i]);
        else fprintf(ctd," %d", converted[i]);
    }
    
    fclose(pk);
    fclose(ctd);

    return 0;
}

