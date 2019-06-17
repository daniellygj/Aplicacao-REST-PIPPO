num_foi,num_voltou = [int(i) for i in input().split(' ')]
id_dos_q_voltaram = input().split(' ')
nao_voltaro = []
for i in range(1,num_foi+1):
    if str(i) not in id_dos_q_voltaram:
        nao_voltaro.append(i)
resp = '*'
if len(nao_voltaro) != 0:
    resp = ''
    for id in nao_voltaro:
        resp += str(id) + ' '
    resp = resp.strip()

print(resp)