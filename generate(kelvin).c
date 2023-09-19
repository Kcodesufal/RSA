#include <stdio.h>

int ehprimo(long long int number)
{
	if (number <= 1) return 0;

    for (int i = 2; i <= number / 2; ++i)
    {
        if (number % i == 0) return 0;
    }

    return 1;
}

void list_prime(long long int start, long long int phi)
{
    int count = 0;
    for (long long int i = start; i < phi; ++i)
    {
        if (ehprimo(i))
        {
            printf(" %Ld", i);
            count++;
        }
        if (count == 10) return;
    }
}

int main()
{
    FILE *pk;
    pk = fopen("public_key.txt", "w");
    long long int p, q, n, phi, e;

    do
    {
        printf("say a P prime\n");
        scanf("%Ld", &p);

    } while(!ehprimo(p));

    do
    {
        printf("say a Q prime\n");
        scanf("%Ld", &q);

    } while(!ehprimo(q));

    n = p * q;
    phi = (p - 1) * (q - 1);

    printf("Choose a 'e', it MUST be 1 < e < phi, and MUST be a phi coprime\n");
    printf("here you got some options, feel free to choose:\n");

    list_prime(2, phi);

    printf("\nhave you choosed yet?\n");

    scanf("%Ld", &e);

    printf("your generated public key is:\n");
    printf("(n, e)\n(%Ld, %Ld)\n", n, e);

    fprintf(pk, "%Ld %Ld\n", n, e);


    fclose(pk);
    
    return 0;
}

